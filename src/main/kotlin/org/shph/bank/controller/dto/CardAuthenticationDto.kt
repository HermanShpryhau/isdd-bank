package org.shph.bank.controller.dto

data class CardAuthenticationDto(
    val cardNumber: String = "",
    val pin: String = ""
)
