package org.shph.bank.controller.dto

import java.math.BigDecimal

data class AccountDto(
    val id: Long?,
    val accountNumber: String,
    val accountType: String,
    val ownerName: String,
    val ownerId: Long,
    val balance: BigDecimal
)
