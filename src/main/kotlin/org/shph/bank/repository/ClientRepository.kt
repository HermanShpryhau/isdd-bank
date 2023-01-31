package org.shph.bank.repository

import org.shph.bank.model.Client
import org.springframework.data.jpa.repository.JpaRepository

interface ClientRepository: JpaRepository<Client, Long> {
    fun findByLastNameAndFirstNameAndMiddleName(lastName: String, firstName: String, middleName: String): List<Client>
}