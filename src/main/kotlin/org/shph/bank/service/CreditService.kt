package org.shph.bank.service

import jakarta.transaction.Transactional
import org.shph.bank.controller.dto.CreditEntityDto
import org.shph.bank.model.Account
import org.shph.bank.model.Credit
import org.shph.bank.repository.*
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.util.*
import kotlin.random.Random

@Service
class CreditService(
    val creditRepository: CreditRepository,
    val depositTypeRepository: DepositTypeRepository,
    val clientService: ClientService,
    val accountRepository: AccountRepository,
    val accountTypeRepository: AccountTypeRepository,
    val currencyRepository: CurrencyRepository,
    val transactionService: TransactionService
) {
    @Transactional
    fun createCredit(creditDto: CreditEntityDto): Credit {
        val owner = creditDto.ownerId?.let { clientService.findById(it) } ?: throw RuntimeException("Owner not found")

        val accountType = creditDto.accountTypeId?.let { accountTypeRepository.findById(it).orElseThrow() }
            ?: throw RuntimeException("Account type not found")

        val creditAccount = Account(
            accountNumber = generateAccountNumber(),
            accountOwner = owner,
            accountBalance = BigDecimal.ZERO,
            accountType = accountType
        )
        accountRepository.save(creditAccount)

        val interestAccount = Account(
            accountNumber = generateAccountNumber(),
            accountOwner = owner,
            accountBalance = BigDecimal.ZERO,
            accountType = accountType
        )
        accountRepository.save(interestAccount)

        val creditType = creditDto.creditTypeId?.let { depositTypeRepository.findById(it).orElseThrow() }
            ?: throw RuntimeException("Deposit type not found")

        val currency = currencyRepository.findByCurrencyName(creditDto.currency)
            ?: throw RuntimeException("Currency not found")

        val credit = creditRepository.save(
            Credit(
                contractNumber = UUID.randomUUID(),
                creditAccount = creditAccount,
                interestAccount = interestAccount,
                initialAmount = creditDto.initialAmount,
                creditType = creditType,
                startDate = creditDto.startDate,
                endDate = creditDto.endDate,
                currency = currency,
                owner = owner
            )
        )

        val bankFundAccount = accountRepository.findById(2).orElseThrow()
        transactionService.createTransaction(bankFundAccount, creditAccount, credit.initialAmount)

        return credit
    }

    private fun generateAccountNumber(): String {
        var result = ""
        for (i in 0..12) {
            result += Random.Default.nextInt(0, 9)
        }
        return result
    }

    @Transactional
    fun applyInterest(creditId: Long) {
        val credit = creditRepository.findById(creditId).orElseThrow()

        val interest = -(credit.initialAmount * credit.creditType.interest)
        transactionService.createTransaction(credit.interestAccount, interest, UUID.randomUUID())
    }

    fun findAll(): List<Credit> {
        return creditRepository.findAll().toList()
    }
}