package org.shph.bank.controller

import org.shph.bank.controller.dto.ClientEntityDto
import org.shph.bank.controller.dto.assembler.DtoAssembler
import org.shph.bank.model.Client
import org.shph.bank.repository.*
import org.shph.bank.service.ClientService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/clients")
class ClientController(
    val clientService: ClientService,
    val clientDtoAssembler: DtoAssembler<Client, ClientEntityDto>,
    val citizenshipRepository: CitizenshipRepository,
    val cityRepository: CityRepository,
    val disabilityRepository: DisabilityRepository,
    val maritalStatusRepository: MaritalStatusRepository
) {
    @GetMapping
    fun viewAll(model: Model): String {
        val clients = clientService.findAll()
        model.addAttribute("clients", clients)
        return "clients/clientsTable"
    }

    @GetMapping("/new")
    fun createNewClient(model: Model): String {
        loadSelectOptions(model)
        model.addAttribute("newClient", true)
        model.addAttribute("client", ClientEntityDto())
        return "clients/clientForm"
    }

    @GetMapping("/{id}")
    fun editClient(@PathVariable id: Long, model: Model): String {
        loadSelectOptions(model)

        model.addAttribute("newClient", false)

        val client = clientService.findById(id)
        if (client != null) {
            model.addAttribute("client", clientDtoAssembler.toDto(client))
        }

        return "clients/clientForm"
    }

    @PostMapping
    fun saveClient(@ModelAttribute dto: ClientEntityDto, model: Model): String {
        clientService.save(clientDtoAssembler.toEntity(dto))
        return "redirect:clients"
    }

    @PostMapping("/delete/{id}")
    fun deleteClient(@PathVariable id: Long): String {
        clientService.delete(id)
        return "redirect:http://localhost:8080/clients"
    }

    fun loadSelectOptions(model: Model) {
        model.addAttribute("cities", cityRepository.findAll())
        model.addAttribute("citizenships", citizenshipRepository.findAll())
        model.addAttribute("disabilities", disabilityRepository.findAll())
        model.addAttribute("maritalStatuses", maritalStatusRepository.findAll())
    }
}