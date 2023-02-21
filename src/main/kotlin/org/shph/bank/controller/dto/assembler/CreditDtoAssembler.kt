package org.shph.bank.controller.dto.assembler

import org.shph.bank.controller.dto.CreditEntityDto
import org.shph.bank.model.Credit
import org.shph.bank.repository.CreditRepository
import org.springframework.stereotype.Component

@Component
class CreditDtoAssembler(
    val creditRepository: CreditRepository
): DtoAssembler<Credit, CreditEntityDto> {
    override fun toEntity(dto: CreditEntityDto): Credit {
        return dto.id?.let { creditRepository.findById(it).orElseThrow() }
            ?: throw RuntimeException("Credit not found")
    }

    override fun toDto(entity: Credit): CreditEntityDto {
        val owner = entity.owner
        val ownerName = owner.lastName + " " + owner.firstName + " " + owner.lastName
        return CreditEntityDto(
            id = entity.id,
            contractNumber = entity.contractNumber.toString(),
            creditAccountNumber = entity.creditAccount.accountNumber,
            interestAccountNumber = entity.interestAccount.accountNumber,
            creditType = entity.creditType.depositTypeName,
            creditTypeId = entity.creditType.id,
            startDate = entity.startDate,
            endDate = entity.endDate,
            currency = entity.currency.currencyName,
            initialAmount = entity.initialAmount,
            interest = -entity.interestAccount.accountBalance,
            ownerId = entity.owner.id,
            ownerName = ownerName,
            accountTypeId = null,
        )
    }
}