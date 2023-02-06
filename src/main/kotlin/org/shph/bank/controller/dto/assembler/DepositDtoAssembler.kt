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
        return DepositDto(
            id = entity.id,
            contractNumber = entity.contractNumber,
            activeAccountNumber = entity.activeAccount.accountNumber,
            passiveAccountNumber = entity.passiveAccount.accountNumber,
            depositType = entity.depositType.depositTypeName,
            depositTypeId = entity.depositType.id,
            startDate = entity.startDate,
            endDate = entity.endDate,
            currency = entity.currency.currencyName,
            initialDeposit = entity.activeAccount.accountBalance,
            ownerId = entity.activeAccount.accountOwner!!.id,
            ownerName = entity.activeAccount.accountOwner!!.lastName,
            accountTypeId = null
        )
    }
}