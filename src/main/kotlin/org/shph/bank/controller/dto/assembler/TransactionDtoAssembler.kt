package org.shph.bank.controller.dto.assembler

import org.shph.bank.controller.dto.TransactionEntityDto
import org.shph.bank.model.Transaction
import org.shph.bank.repository.TransactionRepository
import org.springframework.stereotype.Component
import java.lang.RuntimeException

@Component
class TransactionDtoAssembler(
    val transactionRepository: TransactionRepository
) : DtoAssembler<Transaction, TransactionEntityDto> {
    override fun toEntity(dto: TransactionEntityDto): Transaction {
        return dto.transactionId?.let { transactionRepository.findById(it).orElseThrow() }
            ?: throw RuntimeException("Transaction not found")
    }

    override fun toDto(entity: Transaction): TransactionEntityDto {
        return TransactionEntityDto(
            transactionId = entity.id,
            transactionUUID = entity.transactionUUID,
            amount = entity.amount,
            accountId = entity.account.id,
            accountNumber = entity.account.accountNumber
        )
    }
}