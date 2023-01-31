package org.shph.bank.repository

import org.shph.bank.model.Citizenship
import org.springframework.data.repository.CrudRepository

interface CitizenshipRepository: CrudRepository<Citizenship, Long>