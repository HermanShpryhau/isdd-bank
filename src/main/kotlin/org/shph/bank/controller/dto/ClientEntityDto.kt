package org.shph.bank.controller.dto

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import org.springframework.format.annotation.DateTimeFormat
import java.math.BigDecimal
import java.time.LocalDate

data class ClientEntityDto(
    val id: Long? = null,

    @field:NotNull
    @field:Size(min = 1, max = 250)
    val lastName: String = "",

    @field:NotNull
    @field:Size(min = 1, max = 250)
    val firstName: String = "",

    @field:NotNull
    @field:Size(min = 1, max = 250)
    val middleName: String = "",

    @field:NotNull
    @field:DateTimeFormat(pattern = "yyyy-MM-dd")
    val dateOfBirth: LocalDate = LocalDate.now(),

    @field:NotNull
    @field:Size(max = 1)
    val sex: String = "",

    @field:NotNull
    @field:Size(min = 2, max = 2)
    val passportSeries: String = "",

    @field:NotNull
    @field:Size(min = 7, max = 7)
    val passportNumber: String = "",

    @field:NotNull
    @field:Size(min = 14, max = 14)
    val passportIdNumber: String = "",

    @field:NotNull
    @field:Size(min = 1, max = 250)
    val passportIssuedBy: String= "",

    @field:NotNull
    @field:DateTimeFormat(pattern = "yyyy-MM-dd")
    val passportIssueDate: LocalDate = LocalDate.now(),

    @field:NotNull
    @field:Size(min = 1, max = 250)
    val placeOfBirth: String = "",

    @field:NotNull
    val residentialAddressCityId: Long = -1,

    @field:NotNull
    @field:Size(min = 1, max = 250)
    val residentialAddress: String = "",

    @field:NotNull
    val registrationAddressCityId: Long = -1,

    @field:NotNull
    @field:Size(min = 1, max = 250)
    val registrationAddress: String = "",

    @field:Size(min = 13, max = 14)
    val personalPhoneNumber: String? = null,

    @field:Size(min = 8, max = 8)
    val homePhoneNumber: String? = null,

    @field:Size(min = 1, max = 250)
    val email: String? = null,

    @field:Size(min = 1, max = 250)
    val placeOfWork: String? = null,

    @field:Size(min = 1, max = 250)
    val positionAtWork: String? = null,

    @field:NotNull
    val citizenshipId: Long = -1,

    @field:NotNull
    val maritalStatusId: Long = -1,

    val disabilityId: Long = -1,

    val retiredFlag: Boolean = false,

    val monthlyIncome: BigDecimal? = null
): EntityDto
