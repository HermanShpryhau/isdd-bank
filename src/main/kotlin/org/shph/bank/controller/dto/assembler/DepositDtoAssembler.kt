package org.shph.bank.controller.dto.assembler

import org.shph.bank.controller.dto.DepositDto
import org.shph.bank.model.Deposit
import org.shph.bank.repository.DepositRepository
import org.springframework.stereotype.Component

@Component
class DepositDtoAssembler(
    val depositRepository: DepositRepository
): DtoAssembler<Deposit, DepositDto> {
    override fun toEntity(dto: DepositDto): Deposit {
        return dto.id?.let { depositRepository.findById(it).orElseThrow() }
            ?: throw RuntimeException("Deposit not found")
    }

    override fun toDto(entity: Deposit): DepositDto {
        val owner = entity.owner
        val ownerName = owner.lastName + " " + owner.firstName + " " + owner.lastName
        return DepositDto(
            id = entity.id,
            contractNumber = entity.contractNumber,
            depositAccountNumber = entity.depositAccount.accountNumber,
            interestAccountNumber = entity.interestAccount.accountNumber,
            depositType = entity.depositType.depositTypeName,
            depositTypeId = entity.depositType.id,
            startDate = entity.startDate,
            endDate = entity.endDate,
            currency = entity.currency.currencyName,
            initialDeposit = entity.depositAccount.accountBalance,
            interest = entity.interestAccount.accountBalance,
            ownerId = entity.owner.id,
            ownerName = ownerName,
            accountTypeId = null,
            recallable = entity.depositType.recallable
        )
    }
}