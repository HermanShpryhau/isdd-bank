package org.shph.bank.service.impl

import org.shph.bank.model.Client
import org.shph.bank.repository.ClientRepository
import org.shph.bank.service.ClientService
import org.springframework.stereotype.Service

@Service
class ClientServiceImpl(val clientRepository: ClientRepository): ClientService {
    override fun findById(id: Long): Client? {
        return clientRepository.findById(id).orElse(null)
    }

    override fun save(client: Client): Client {
        return clientRepository.save(client)
    }
}