package org.shph.bank.controller.dto

import org.springframework.format.annotation.DateTimeFormat
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime

data class ClientDto(
    val id: Long? = null,
    val lastName: String = "",
    val firstName: String = "",
    val middleName: String = "",
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    val dateOfBirth: LocalDate = LocalDate.now(),
    val sex: String = "",
    val passportSeries: String = "",
    val passportNumber: String = "",
    val passportIdNumber: String = "",
    val passportIssuedBy: String= "",
    val passportIssueDate: LocalDate = LocalDate.now(),
    val placeOfBirth: String = "",
    val residentialAddressCityId: Long = -1,
    val residentialAddress: String = "",
    val registrationAddressCityId: Long = -1,
    val registrationAddress: String = "",
    val personalPhoneNumber: String? = null,
    val homePhoneNumber: String? = null,
    val email: String? = null,
    val placeOfWork: String? = null,
    val positionAtWork: String? = null,
    val citizenshipId: Long = -1,
    val maritalStatusId: Long = -1,
    val disabilityId: Long = -1,
    val retiredFlag: Boolean = false,
    val monthlyIncome: BigDecimal? = null
): Dto
