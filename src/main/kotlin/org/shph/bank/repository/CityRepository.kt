package org.shph.bank.repository

import org.shph.bank.model.City
import org.springframework.data.repository.CrudRepository

interface CityRepository: CrudRepository<City, Long>