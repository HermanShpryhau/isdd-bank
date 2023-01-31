package org.shph.bank.service

import org.shph.bank.model.Client

interface ClientService {
    fun findById(id: Long): Client?

    fun save(client: Client): Client
}