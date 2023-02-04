package org.shph.bank.controller.dto.assembler

import org.shph.bank.controller.dto.TransactionDto
import org.shph.bank.model.Transaction
import org.shph.bank.repository.AccountRepository
import org.springframework.stereotype.Component

@Component
class TransactionDtoAssembler(
    val accountRepository: AccountRepository
) : DtoAssembler<Transaction, TransactionDto> {
    override fun toEntity(dto: TransactionDto): Transaction {
        val sourceAccount = accountRepository.findById(dto.sourceAccountId).orElseThrow()
        val destinationAccount = accountRepository.findById(dto.destinationAccountId).orElseThrow()

        return Transaction(sourceAccount = sourceAccount, destinationAccount = destinationAccount, amount = dto.amount)
    }

    override fun toDto(entity: Transaction): TransactionDto {
        return TransactionDto(entity.id, entity.sourceAccount.id!!, entity.destinationAccount.id!!, entity.amount)
    }
}