package org.shph.bank.service

import org.shph.bank.model.Client

interface ClientService {
    fun findAll(): List<Client>

    fun findById(id: Long): Client?

    fun save(client: Client): Client

    fun canSaveClient(client: Client): Boolean

    fun delete(id: Long)
}