package org.shph.bank.controller.dto

import java.math.BigDecimal
import java.time.LocalDate

data class DepositDto(
    val id: Long?,
    val contractNumber: String,
    val ownerId: Long?,
    val ownerName: String?,
    val activeAccountNumber: String?,
    val passiveAccountNumber: String?,
    val accountTypeId: Long?,
    val depositTypeId: Long?,
    val depositType: String,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val initialDeposit: BigDecimal,
    val interest: BigDecimal,
    val currency: String,
    val recallable: Boolean
): EntityDto
