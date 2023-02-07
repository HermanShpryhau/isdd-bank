package org.shph.bank.controller

import org.shph.bank.controller.dto.assembler.DepositDtoAssembler
import org.shph.bank.repository.DepositTypeRepository
import org.shph.bank.service.ClientService
import org.shph.bank.service.DepositService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/deposits")
class DepositController(
    val depositService: DepositService,
    val depositDtoAssembler: DepositDtoAssembler,
    val clientService: ClientService,
    val depositTypeRepository: DepositTypeRepository
) {
    @GetMapping
    fun viewAll(model: Model): String {
        val deposits = depositService.findAll().map{deposit -> depositDtoAssembler.toDto(deposit)}
        model.addAttribute("deposits", deposits)

        model.addAttribute("depositTypes", depositTypeRepository.findAll())
        model.addAttribute("clients", clientService.findAll())

        return "deposits/depositsTable"
    }

    @PostMapping("/{id}/payinterest")
    fun payInterest(@PathVariable id: Long): String {
        depositService.payInterest(id)
        return "deposits/depositsTable"
    }

    @PostMapping("/{id}/close")
    fun closeDeposit(@PathVariable id: Long): String {
        depositService.closeDeposit(id)
        return "deposits/depositsTable"
    }
}