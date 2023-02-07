package org.shph.bank.repository

import org.shph.bank.model.Deposit
import org.springframework.data.jpa.repository.JpaRepository

interface DepositRepository: JpaRepository<Deposit, Long> {
    fun findByActiveTrue(): List<Deposit>
}