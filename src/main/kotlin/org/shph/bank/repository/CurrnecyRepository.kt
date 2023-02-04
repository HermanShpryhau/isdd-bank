package org.shph.bank.repository

import org.shph.bank.model.Currency
import org.springframework.data.repository.CrudRepository

interface CurrencyRepository: CrudRepository<Currency, Long>