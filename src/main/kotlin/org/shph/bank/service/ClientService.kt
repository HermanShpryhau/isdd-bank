package org.shph.bank.service

import org.shph.bank.model.Client
import org.shph.bank.repository.ClientRepository
import org.springframework.stereotype.Service
import java.lang.RuntimeException

@Service
class ClientService(val clientRepository: ClientRepository) {
    fun findAll(): List<Client> {
        return clientRepository.findAll()
    }

    fun findById(id: Long): Client? {
        return clientRepository.findById(id).orElse(null)
    }

    fun save(client: Client): Client {
        if (!canSaveClient(client))
            throw RuntimeException("Client already exists.")
        return clientRepository.save(client)
    }

    private fun canSaveClient(client: Client): Boolean {
        return clientRepository.findByLastNameAndFirstNameAndMiddleNameAndPassportIdNumber(
            client.lastName,
            client.firstName,
            client.middleName,
            client.passportIdNumber
        ).isEmpty()
    }

    fun delete(id: Long) {
        clientRepository.deleteById(id)
    }
}