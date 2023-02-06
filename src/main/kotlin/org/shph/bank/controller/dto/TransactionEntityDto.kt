package org.shph.bank.controller.dto

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive
import java.math.BigDecimal

data class TransactionEntityDto(
    @field:NotNull
    val transactionId: Long?,

    @field:NotNull
    val sourceAccountId: Long,

    @field:NotNull
    val destinationAccountId: Long,

    @field:NotNull
    @field:Positive
    val amount: BigDecimal
): EntityDto
