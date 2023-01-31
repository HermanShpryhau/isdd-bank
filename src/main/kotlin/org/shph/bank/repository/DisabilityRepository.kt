package org.shph.bank.repository

import org.shph.bank.model.Disability
import org.springframework.data.repository.CrudRepository

interface DisabilityRepository: CrudRepository<Disability, Long>