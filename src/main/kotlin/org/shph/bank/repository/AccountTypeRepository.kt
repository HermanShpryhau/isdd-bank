package org.shph.bank.repository

import org.shph.bank.model.AccountType
import org.springframework.data.repository.CrudRepository

interface AccountTypeRepository: CrudRepository<AccountType, Long> {
    fun findByAccountTypeCode(accountTypeCode: String): AccountType?
}