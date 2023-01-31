package org.shph.bank.repository

import org.shph.bank.model.Address
import org.springframework.data.repository.CrudRepository

interface AddressRepository: CrudRepository<Address, Long>