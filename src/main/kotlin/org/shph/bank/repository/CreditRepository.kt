package org.shph.bank.repository

import org.shph.bank.model.Credit
import org.springframework.data.repository.CrudRepository

interface CreditRepository: CrudRepository<Credit, Long>