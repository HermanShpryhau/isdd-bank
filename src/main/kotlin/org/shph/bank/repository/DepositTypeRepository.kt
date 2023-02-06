package org.shph.bank.repository

import org.shph.bank.model.DepositType
import org.springframework.data.repository.CrudRepository

interface DepositTypeRepository: CrudRepository<DepositType, Long> {
    fun findByDepositTypeName(depositTypeName: String): DepositType?
}