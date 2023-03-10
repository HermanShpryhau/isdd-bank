package org.shph.bank.repository

import org.shph.bank.model.Account
import org.shph.bank.model.Transaction
import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface TransactionRepository: CrudRepository<Transaction, Long> {
    fun findByCommitted(committed: Boolean): List<Transaction>

    fun findByAccount(account: Account): List<Transaction>

    fun findByTransactionUUID(transactionUUID: UUID): List<Transaction>
}