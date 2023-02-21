package org.shph.bank.controller.dto

import java.math.BigDecimal
import java.time.LocalDate

data class CreditEntityDto(
    val id: Long? = null,
    val contractNumber: String = "",
    val ownerId: Long? = null,
    val ownerName: String? = null,
    val creditAccountNumber: String? = null,
    val interestAccountNumber: String? = null,
    val accountTypeId: Long? = -1,
    val creditTypeId: Long? = -1,
    val creditType: String = "",
    val startDate: LocalDate = LocalDate.now(),
    val endDate: LocalDate = LocalDate.now(),
    val initialAmount: BigDecimal = BigDecimal.ZERO,
    val interest: BigDecimal = BigDecimal.ZERO,
    val currency: String = ""
): EntityDto
