package org.shph.bank.repository

import org.shph.bank.model.Account
import org.springframework.data.jpa.repository.JpaRepository

interface AccountRepository: JpaRepository<Account, Long>