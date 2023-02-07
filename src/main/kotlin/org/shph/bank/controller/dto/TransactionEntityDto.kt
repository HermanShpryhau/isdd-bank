package org.shph.bank.controller.dto

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive
import java.math.BigDecimal
import java.util.UUID

data class TransactionEntityDto(
    @field:NotNull
    val transactionId: Long?,

    @field:NotNull
    val transactionUUID: UUID,

    @field:NotNull
    val accountId: Long?,

    @field:NotNull
    val accountNumber: String,

    @field:NotNull
    @field:Positive
    val amount: BigDecimal
): EntityDto
