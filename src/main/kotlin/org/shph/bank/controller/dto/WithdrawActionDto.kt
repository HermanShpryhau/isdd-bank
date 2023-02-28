package org.shph.bank.controller.dto

import java.math.BigDecimal

data class WithdrawActionDto(
    val accountId: Long? = null,
    val balance: BigDecimal = BigDecimal.ZERO,
    val amount: BigDecimal = BigDecimal.ZERO
)
