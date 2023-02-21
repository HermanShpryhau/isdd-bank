package org.shph.bank.controller

import org.shph.bank.controller.dto.CreditEntityDto
import org.shph.bank.controller.dto.assembler.ClientDtoAssembler
import org.shph.bank.controller.dto.assembler.CreditDtoAssembler
import org.shph.bank.repository.AccountTypeRepository
import org.shph.bank.repository.CurrencyRepository
import org.shph.bank.repository.DepositTypeRepository
import org.shph.bank.service.ClientService
import org.shph.bank.service.CreditService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import java.util.*

@Controller
@RequestMapping("/credits")
class CreditController(
    val creditService: CreditService,
    val creditDtoAssembler: CreditDtoAssembler,
    val clientService: ClientService,
    val depositTypeRepository: DepositTypeRepository,
    val currencyRepository: CurrencyRepository,
    val accountTypeRepository: AccountTypeRepository,
    val clientDtoAssembler: ClientDtoAssembler
) {
    @GetMapping
    fun viewAll(model: Model): String {
        val credits = creditService.findAll().map{credit -> creditDtoAssembler.toDto(credit)}
        model.addAttribute("credits", credits)

        return "credits/creditsTable"
    }

    @GetMapping("/new")
    fun createNewDeposit(model: Model): String {
        val newCredit = CreditEntityDto(contractNumber = UUID.randomUUID().toString())
        model.addAttribute("credit", newCredit)
        model.addAttribute("creditTypes", depositTypeRepository.findAll())
        model.addAttribute("clients", clientService.findAll().map { c -> clientDtoAssembler.toDto(c) })
        model.addAttribute("currencies", currencyRepository.findAll())
        model.addAttribute("accountTypes", accountTypeRepository.findAll())
        return "credits/creditsForm"
    }

    @PostMapping("/new")
    fun saveNewDeposit(@ModelAttribute creditDto: CreditEntityDto, model: Model): String {
        creditService.createCredit(creditDto)
        return "redirect:http://localhost:8080/deposits"
    }

    @PostMapping("/{id}/applyinterest")
    fun payInterest(@PathVariable id: Long): String {
        creditService.applyInterest(id)
        return "redirect:http://localhost:8080/credits"
    }
}