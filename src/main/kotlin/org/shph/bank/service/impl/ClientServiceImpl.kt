package org.shph.bank.service.impl

import org.shph.bank.model.Client
import org.shph.bank.repository.ClientRepository
import org.shph.bank.service.ClientService
import org.springframework.stereotype.Service
import java.lang.RuntimeException

@Service
class ClientServiceImpl(val clientRepository: ClientRepository) : ClientService {
    override fun findAll(): List<Client> {
        return clientRepository.findAll()
    }

    override fun findById(id: Long): Client? {
        return clientRepository.findById(id).orElse(null)
    }

    override fun save(client: Client): Client {
        if (!canSaveClient(client))
            throw RuntimeException("Client already exists.")
        return clientRepository.save(client)
    }

    override fun canSaveClient(client: Client): Boolean {
        return clientRepository.findByLastNameAndFirstNameAndMiddleNameAndPassportIdNumber(
            client.lastName,
            client.firstName,
            client.middleName,
            client.passportIdNumber
        ).isEmpty()
    }

    override fun delete(id: Long) {
        clientRepository.deleteById(id)
    }
}