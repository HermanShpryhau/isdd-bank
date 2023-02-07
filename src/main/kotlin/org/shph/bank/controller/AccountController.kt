package org.shph.bank.controller

import org.shph.bank.controller.dto.assembler.AccountDtoAssembler
import org.shph.bank.controller.dto.assembler.TransactionDtoAssembler
import org.shph.bank.repository.AccountRepository
import org.shph.bank.repository.TransactionRepository
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/accounts")
class AccountController(
    val accountRepository: AccountRepository,
    val accountDtoAssembler: AccountDtoAssembler,
    val transactionRepository: TransactionRepository,
    val transactionDtoAssembler: TransactionDtoAssembler
) {
    @GetMapping
    fun viewAll(model: Model): String {
        val accounts = accountRepository.findAll().map { account -> accountDtoAssembler.toDto(account) }
        model.addAttribute("accounts", accounts)
        return "accounts/accountTable"
    }

    @GetMapping("/{id}")
    fun viewDetails(@PathVariable id: Long, model: Model): String {
        val account = accountRepository.findById(id).orElseThrow()
        val transactions = transactionRepository.findByAccount(account).map { t -> transactionDtoAssembler.toDto(t) }

        model.addAttribute("account", accountDtoAssembler.toDto(account))
        model.addAttribute("transactions", transactions)

        return "accounts/accountDetails"
    }
}