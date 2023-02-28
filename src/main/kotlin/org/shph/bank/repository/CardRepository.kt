package org.shph.bank.repository

import org.shph.bank.model.Card
import org.springframework.data.repository.CrudRepository

interface CardRepository: CrudRepository<Card, Long> {
    fun findByCardNumberAndPin(cardNumber: String, pin: String): Card?
}