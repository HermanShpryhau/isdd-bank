package org.shph.bank.repository

import org.shph.bank.model.Account
import org.shph.bank.model.Transaction
import org.springframework.data.repository.CrudRepository

interface TransactionRepository: CrudRepository<Transaction, Long> {
    fun findByCommitted(committed: Boolean): List<Transaction>

    fun findByAccount(account: Account): List<Transaction>
}