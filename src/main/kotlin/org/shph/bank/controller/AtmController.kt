package org.shph.bank.controller

import com.lowagie.text.Document
import com.lowagie.text.Font
import com.lowagie.text.Paragraph
import com.lowagie.text.pdf.PdfWriter
import org.shph.bank.controller.dto.CardAuthenticationDto
import org.shph.bank.controller.dto.WithdrawActionDto
import org.shph.bank.repository.AccountRepository
import org.shph.bank.repository.CardRepository
import org.shph.bank.service.TransactionService
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.FileSystemResource
import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import org.springframework.web.servlet.view.RedirectView
import java.io.File
import java.io.FileOutputStream
import java.util.*

@Controller
@RequestMapping("/atm")
class AtmController(
    val cardRepository: CardRepository,
    val transactionService: TransactionService,
    val accountRepository: AccountRepository
) {
    @Value("\${baseFilePath}")
    lateinit var baseFilePath: String

    @GetMapping
    fun view(model: Model, @RequestParam invalidData: Boolean?): String {
        model.addAttribute("card", CardAuthenticationDto())
        invalidData?.let { model.addAttribute("invalidData", it) }
        return "atm/atm"
    }

    @PostMapping("/authenticate")
    fun authenticateCard(cardAuthenticationDto: CardAuthenticationDto, redirectAttributes: RedirectAttributes): RedirectView {
        val card = cardRepository.findByCardNumberAndPin(cardAuthenticationDto.cardNumber, cardAuthenticationDto.pin)
        return if (card != null) {
            redirectAttributes.addAttribute("cardId", card.id)
            RedirectView("actions/withdraw")
        } else {
            redirectAttributes.addAttribute("invalidData", true)
            RedirectView("/atm", true)
        }
    }

    @GetMapping("/actions/withdraw")
    fun viewWithdrawAction(@RequestParam cardId: Long?, model: Model): String {
        val card = cardId?.let { cardRepository.findById(it).orElseThrow() } ?: throw RuntimeException("Card $cardId not found.")
        model.addAttribute("withdrawal", WithdrawActionDto(card.account.id, card.account.accountBalance))
        return "atm/atmWithdrawAction"
    }

    @PostMapping("/actions/withdraw")
    fun executeWithdrawAction(@ModelAttribute withdrawal: WithdrawActionDto, model: Model, redirectAttributes: RedirectAttributes): RedirectView {
        val account = withdrawal.accountId?.let { accountRepository.findById(it).orElseThrow() }
            ?: throw RuntimeException("Account not found")

        val transaction = transactionService.createTransaction(account, -withdrawal.amount, UUID.randomUUID())

        redirectAttributes.addAttribute("transactionUuid", transaction.transactionUUID)
        return RedirectView("/atm/check", true)
    }

    @GetMapping("/check", produces = [MediaType.APPLICATION_OCTET_STREAM_VALUE])
    @ResponseBody
    fun getCheck(@RequestParam transactionUuid: UUID): FileSystemResource {
        val transaction = transactionService.findByTransactionUUID(transactionUuid) ?: throw RuntimeException("Transaction $transactionUuid not found.")

        val pdfOutputStream = FileOutputStream("$baseFilePath/$transactionUuid-check.pdf")
        val checkPdf = Document()
        val pdfWriter = PdfWriter.getInstance(checkPdf, pdfOutputStream)
        checkPdf.apply {
            open()
            add(Paragraph("Чек ${transaction.transactionUUID}", Font(Font.HELVETICA, 16f, Font.BOLD)))
            add(Paragraph("Счет: ${transaction.account.accountNumber}"))
            add(Paragraph("Сумма списания: ${-transaction.amount}"))
            close()
        }
        pdfWriter.close()

        return FileSystemResource(File("$baseFilePath/$transactionUuid-check.pdf"))
    }
}