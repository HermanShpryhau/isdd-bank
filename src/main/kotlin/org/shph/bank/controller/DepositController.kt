package org.shph.bank.controller

import org.shph.bank.controller.dto.DepositEntityDto
import org.shph.bank.controller.dto.assembler.ClientDtoAssembler
import org.shph.bank.controller.dto.assembler.DepositDtoAssembler
import org.shph.bank.repository.AccountTypeRepository
import org.shph.bank.repository.CurrencyRepository
import org.shph.bank.repository.DepositTypeRepository
import org.shph.bank.service.ClientService
import org.shph.bank.service.DepositService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import java.util.UUID

@Controller
@RequestMapping("/deposits")
class DepositController(
    val depositService: DepositService,
    val depositDtoAssembler: DepositDtoAssembler,
    val clientService: ClientService,
    val depositTypeRepository: DepositTypeRepository,
    val currencyRepository: CurrencyRepository,
    val accountTypeRepository: AccountTypeRepository,
    val clientDtoAssembler: ClientDtoAssembler
) {
    @GetMapping
    fun viewAll(model: Model): String {
        val deposits = depositService.findAll().map{deposit -> depositDtoAssembler.toDto(deposit)}
        model.addAttribute("deposits", deposits)

        return "deposits/depositsTable"
    }

    @GetMapping("/new")
    fun createNewDeposit(model: Model): String {
        val newDeposit = DepositEntityDto(contractNumber = UUID.randomUUID().toString())
        model.addAttribute("deposit", newDeposit)
        model.addAttribute("depositTypes", depositTypeRepository.findAll())
        model.addAttribute("clients", clientService.findAll().map { c -> clientDtoAssembler.toDto(c) })
        model.addAttribute("currencies", currencyRepository.findAll())
        model.addAttribute("accountTypes", accountTypeRepository.findAll())
        return "deposits/depositForm"
    }

    @PostMapping("/new")
    fun saveNewDeposit(@ModelAttribute depositDto: DepositEntityDto, model: Model): String {
        depositService.createDeposit(depositDto)
        return "redirect:http://localhost:8080/deposits"
    }

    @PostMapping("/{id}/payinterest")
    fun payInterest(@PathVariable id: Long): String {
        depositService.payInterest(id)
        return "redirect:http://localhost:8080/deposits"
    }

    @PostMapping("/{id}/close")
    fun closeDeposit(@PathVariable id: Long): String {
        depositService.closeDeposit(id)
        return "redirect:http://localhost:8080/deposits"
    }
}