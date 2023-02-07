package org.shph.bank.service

import jakarta.transaction.Transactional
import org.shph.bank.controller.dto.DepositEntityDto
import org.shph.bank.model.Account
import org.shph.bank.model.Deposit
import org.shph.bank.repository.*
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.util.UUID
import kotlin.random.Random

@Service
class DepositService(
    val depositRepository: DepositRepository,
    val depositTypeRepository: DepositTypeRepository,
    val clientService: ClientService,
    val accountRepository: AccountRepository,
    val accountTypeRepository: AccountTypeRepository,
    val currencyRepository: CurrencyRepository,
    val transactionService: TransactionService
) {
    fun findAll(): List<Deposit> {
        return depositRepository.findByActiveTrue()
    }

    @Transactional
    fun createDeposit(depositDto: DepositEntityDto): Deposit {
        val owner = depositDto.ownerId?.let { clientService.findById(it) } ?: throw RuntimeException("Owner not found")

        val accountType = depositDto.accountTypeId?.let { accountTypeRepository.findById(it).orElseThrow() }
            ?: throw RuntimeException("Account type not found")

        val depositAccount = Account(
            accountNumber = generateAccountNumber(),
            accountOwner = owner,
            accountBalance = BigDecimal.ZERO,
            accountType = accountType
        )
        accountRepository.save(depositAccount)

        val interestAccount = Account(
            accountNumber = generateAccountNumber(),
            accountOwner = owner,
            accountBalance = BigDecimal.ZERO,
            accountType = accountType
        )
        accountRepository.save(interestAccount)

        val depositType = depositDto.depositTypeId?.let { depositTypeRepository.findById(it).orElseThrow() }
            ?: throw RuntimeException("Deposit type not found")

        val currency = currencyRepository.findByCurrencyName(depositDto.currency)
            ?: throw RuntimeException("Currency not found")

        val deposit = depositRepository.save(
            Deposit(
                contractNumber = UUID.randomUUID().toString(),
                depositAccount = depositAccount,
                interestAccount = interestAccount,
                depositType = depositType,
                startDate = depositDto.startDate,
                endDate = depositDto.endDate,
                nextInterestPayDate = depositDto.startDate.plusMonths(1),
                currency = currency,
                owner = owner
            )
        )

        val bankAccount = accountRepository.findById(1).orElseThrow()
        val transactionUUID = UUID.randomUUID()
        transactionService.createTransaction(bankAccount, depositDto.initialDeposit, transactionUUID)
        transactionService.createTransaction(bankAccount, depositAccount, depositDto.initialDeposit, transactionUUID)

        return deposit
    }

    private fun generateAccountNumber(): String {
        var result = ""
        for (i in 0..12) {
            result += Random.Default.nextInt(0, 9)
        }
        return result
    }

    @Transactional
    fun payInterest(depositId: Long) {
        val deposit = depositRepository.findById(depositId).orElseThrow()

        val interest = deposit.depositType.interest
        val addedAmount = deposit.depositAccount.accountBalance * interest

        val bankFundAccount = accountRepository.findById(2).orElseThrow()
        transactionService.createTransaction(bankFundAccount, deposit.interestAccount, addedAmount)
    }

    @Transactional
    fun closeDeposit(depositId: Long) {
        val deposit = depositRepository.findById(depositId).orElseThrow()

        val depositAmount = deposit.depositAccount.accountBalance
        val interestAmount = deposit.interestAccount.accountBalance
        val transactionUUID = UUID.randomUUID()

        val bankAccount = accountRepository.findById(1).orElseThrow()
        transactionService.createTransaction(deposit.depositAccount, bankAccount, depositAmount, transactionUUID)
        transactionService.createTransaction(bankAccount, -depositAmount, transactionUUID)

        transactionService.createTransaction(deposit.interestAccount, bankAccount, interestAmount, transactionUUID)
        transactionService.createTransaction(bankAccount, -interestAmount, transactionUUID)

        deposit.active = false
        depositRepository.save(deposit)
    }
}