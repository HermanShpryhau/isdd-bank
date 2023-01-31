package org.shph.bank.repository

import org.shph.bank.model.MaritalStatus
import org.springframework.data.repository.CrudRepository

interface MaritalStatusRepository: CrudRepository<MaritalStatus, Long>