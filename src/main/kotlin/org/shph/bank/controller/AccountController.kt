package org.shph.bank.controller

import org.shph.bank.controller.dto.assembler.AccountDtoAssembler
import org.shph.bank.repository.AccountRepository
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/accounts")
class AccountController(
    val accountRepository: AccountRepository,
    val accountDtoAssembler: AccountDtoAssembler
) {
    @GetMapping
    fun viewAll(model: Model): String {
        val accounts = accountRepository.findAll().map { account -> accountDtoAssembler.toDto(account) }
        model.addAttribute("accounts", accounts)
        return "accounts/accountTable"
    }
}