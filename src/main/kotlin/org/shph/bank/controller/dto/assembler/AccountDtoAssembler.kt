package org.shph.bank.controller.dto.assembler

import org.shph.bank.controller.dto.AccountEntityDto
import org.shph.bank.model.Account
import org.shph.bank.repository.AccountTypeRepository
import org.shph.bank.repository.ClientRepository
import org.springframework.stereotype.Component
import java.lang.RuntimeException

@Component
class AccountDtoAssembler(
    val clientRepository: ClientRepository,
    val accountTypeRepository: AccountTypeRepository
): DtoAssembler<Account, AccountEntityDto> {
    override fun toEntity(dto: AccountEntityDto): Account {
        val owner = clientRepository.findById(dto.ownerId).orElseThrow()

        val accountTypeCode = dto.accountType.split(" ")[0]
        val accountType = accountTypeRepository.findByAccountTypeCode(accountTypeCode)
            ?: throw RuntimeException("Cannot find account type with code $accountTypeCode")

        return Account(
            id = dto.id,
            accountNumber = dto.accountNumber,
            accountType = accountType,
            accountOwner = owner,
            accountBalance = dto.balance
        )
    }

    override fun toDto(entity: Account): AccountEntityDto {
        val accountType = entity.accountType
        val accountTypeDisplayName = accountType.accountTypeCode + " " + accountType.accountTypeName

        val ownerName = if (entity.accountOwner != null) {
            entity.accountOwner?.lastName + " " + entity.accountOwner?.firstName
        } else {
            "БАНК"
        }

        return AccountEntityDto(
            id = entity.id,
            accountNumber = entity.accountNumber,
            accountType = accountTypeDisplayName,
            ownerName = ownerName,
            ownerId = entity.accountOwner?.id ?: -1L,
            balance = entity.accountBalance
        )
    }
}