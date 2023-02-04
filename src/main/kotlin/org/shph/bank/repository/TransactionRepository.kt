package org.shph.bank.repository

import org.shph.bank.model.Transaction
import org.springframework.data.repository.CrudRepository

interface TransactionRepository: CrudRepository<Transaction, Long>