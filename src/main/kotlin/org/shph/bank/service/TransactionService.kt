package org.shph.bank.service

import jakarta.transaction.Transactional
import org.shph.bank.model.Account
import org.shph.bank.model.Transaction
import org.shph.bank.repository.AccountRepository
import org.shph.bank.repository.TransactionRepository
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.util.UUID

@Service
class TransactionService(
    val transactionRepository: TransactionRepository,
    val accountRepository: AccountRepository
) {
    @Transactional
    fun createTransaction(from: Account, to: Account, amount: BigDecimal, uuid: UUID? = null) {
        val transactionUUID = uuid ?: UUID.randomUUID()

        createTransaction(from, -amount, transactionUUID)

        createTransaction(to, amount, transactionUUID)
    }

    fun createTransaction(targetAccount: Account, amount: BigDecimal, transactionUUID: UUID): Transaction {
        return transactionRepository.save(
            Transaction(
                transactionUUID = transactionUUID,
                account = targetAccount,
                amount = amount
            )
        )
    }

    @Transactional
    @Scheduled(cron = "@midnight")
    fun commitTransactions() {
        transactionRepository.findByCommitted(false)
            .forEach { transaction ->
                val account = transaction.account
                account.accountBalance += transaction.amount
                accountRepository.save(account)

                transaction.committed = true
                transactionRepository.save(transaction)
            }
    }
}