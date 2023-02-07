package org.shph.bank.controller.dto

import java.math.BigDecimal
import java.time.LocalDate

data class DepositDto(
    val id: Long? = null,
    val contractNumber: String = "",
    val ownerId: Long? = null,
    val ownerName: String? = null,
    val depositAccountNumber: String? = null,
    val interestAccountNumber: String? = null,
    val accountTypeId: Long? = null,
    val depositTypeId: Long? = null,
    val depositType: String = "",
    val startDate: LocalDate = LocalDate.now(),
    val endDate: LocalDate = LocalDate.now(),
    val initialDeposit: BigDecimal = BigDecimal.ZERO,
    val interest: BigDecimal = BigDecimal.ZERO,
    val currency: String = "",
    val recallable: Boolean = false
): EntityDto
