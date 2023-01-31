package org.shph.bank.controller

import org.shph.bank.controller.dto.ClientDto
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
    val clientDtoAssembler: DtoAssembler<Client, ClientDto>,
    val citizenshipRepository: CitizenshipRepository,
    val cityRepository: CityRepository,
    val disabilityRepository: DisabilityRepository,
    val maritalStatusRepository: MaritalStatusRepository
) {
    @GetMapping("/new")
    fun createNewClient(model: Model): String {
        loadSelectOptions(model)
        model.addAttribute("newClient", true)
        model.addAttribute("client", ClientDto())
        return "clientForm"
    }

    @GetMapping("/{id}")
    fun editClient(@PathVariable id: Long, model: Model): String {
        loadSelectOptions(model)

        model.addAttribute("newClient", false)

        val client = clientService.findById(id)
        if (client != null) {
            model.addAttribute("client", clientDtoAssembler.toDto(client))
        }

        return "clientForm"
    }

    @PostMapping
    fun saveClient(@ModelAttribute dto: ClientDto, model: Model): String {
        loadSelectOptions(model)

        val client = clientService.save(clientDtoAssembler.toEntity(dto))
        model.addAttribute("client", clientDtoAssembler.toDto(client))

        return "redirect:/clients/${client.id}"
    }

    fun loadSelectOptions(model: Model) {
        model.addAttribute("cities", cityRepository.findAll())
        model.addAttribute("citizenships", citizenshipRepository.findAll())
        model.addAttribute("disabilities", disabilityRepository.findAll())
        model.addAttribute("maritalStatuses", maritalStatusRepository.findAll())
    }
}