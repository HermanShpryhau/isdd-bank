package org.shph.bank.service

import jakarta.transaction.Transactional
import org.shph.bank.controller.dto.DepositDto
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
    val currencyRepository: CurrencyRepository
) {
    @Transactional
    fun createDeposit(depositDto: DepositDto): Deposit {
        val owner = depositDto.ownerId?.let { clientService.findById(it) } ?: throw RuntimeException("Owner not found")

        val accountType = depositDto.accountTypeId?.let { accountTypeRepository.findById(it).orElseThrow() }
            ?: throw RuntimeException("Account type not found")

        val activeAccount = Account(
            accountNumber = generateAccountNumber(),
            accountOwner = owner,
            accountBalance = BigDecimal.ZERO,
            accountType = accountType
        )
        accountRepository.save(activeAccount)

        val passiveAccount = Account(
            accountNumber = generateAccountNumber(),
            accountOwner = owner,
            accountBalance = BigDecimal.ZERO,
            accountType = accountType
        )
        accountRepository.save(passiveAccount)

        val depositType = depositTypeRepository.findByDepositTypeName(depositDto.depositType)
            ?: throw RuntimeException("Deposit type not found")

        val currency = currencyRepository.findByCurrencyName(depositDto.currency)
            ?: throw RuntimeException("Currency not found")

        val deposit = Deposit(
            contractNumber = UUID.randomUUID().toString(),
            activeAccount = activeAccount,
            passiveAccount = passiveAccount,
            depositType = depositType,
            startDate = depositDto.startDate,
            endDate = depositDto.endDate,
            nextInterestPayDate = depositDto.startDate.plusMonths(1),
            currency = currency
        )

        return depositRepository.save(deposit)
    }

    private fun generateAccountNumber(): String {
        var result = ""
        for (i in 0..12) {
            result += Random.Default.nextInt()
        }
        return result
    }
}