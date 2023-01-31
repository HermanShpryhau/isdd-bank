package org.shph.bank.model

import jakarta.persistence.*
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import java.math.BigDecimal
import java.time.LocalDate

@Entity
@Table(name = "clients")
open class Client(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id", nullable = false)
    open var id: Long? = null,

    @Size(max = 250)
    @NotNull
    @Column(name = "last_name", nullable = false, length = 250)
    open var lastName: String,

    @Size(max = 250)
    @NotNull
    @Column(name = "first_name", nullable = false, length = 250)
    open var firstName: String,

    @Size(max = 250)
    @NotNull
    @Column(name = "middle_name", nullable = false, length = 250)
    open var middleName: String,

    @NotNull
    @Column(name = "date_of_birth", nullable = false)
    open var dateOfBirth: LocalDate,

    @Size(max = 1)
    @NotNull
    @Column(name = "sex", nullable = false, length = 1)
    open var sex: String,

    @Size(max = 2)
    @NotNull
    @Column(name = "passport_series", nullable = false, length = 2)
    open var passportSeries: String,

    @Size(max = 7)
    @NotNull
    @Column(name = "passport_number", nullable = false, length = 7)
    open var passportNumber: String,

    @Size(max = 14)
    @NotNull
    @Column(name = "passport_id_number", nullable = false, length = 14)
    open var passportIdNumber: String,

    @Size(max = 250)
    @NotNull
    @Column(name = "passport_issued_by", nullable = false, length = 250)
    open var passportIssuedBy: String,

    @NotNull
    @Column(name = "passport_issue_date", nullable = false)
    open var passportIssueDate: LocalDate,

    @Size(max = 250)
    @NotNull
    @Column(name = "place_of_birth", nullable = false, length = 250)
    open var placeOfBirth: String,

    @NotNull
    @OneToOne(fetch = FetchType.LAZY, optional = false, orphanRemoval = true)
    @JoinColumn(name = "residential_address", nullable = false)
    open var residentialAddress: Address,

    @NotNull
    @OneToOne(fetch = FetchType.LAZY, optional = false, orphanRemoval = true)
    @JoinColumn(name = "registration_address", nullable = false)
    open var registrationAddress: Address,

    @Size(max = 15)
    @Column(name = "personal_phone_number", length = 15)
    open var personalPhoneNumber: String? = null,

    @Size(max = 15)
    @Column(name = "home_phone_number", length = 15)
    open var homePhoneNumber: String? = null,

    @Size(max = 250)
    @Column(name = "email", length = 250)
    open var email: String? = null,

    @Size(max = 250)
    @Column(name = "place_of_work", length = 250)
    open var placeOfWork: String? = null,

    @Size(max = 250)
    @Column(name = "position_at_work", length = 250)
    open var positionAtWork: String? = null,

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "citizenship_id", nullable = false)
    open var citizenship: Citizenship,

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "marital_status_id", nullable = false)
    open var maritalStatus: MaritalStatus,

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "disability_id", nullable = false)
    open var disability: Disability,

    @NotNull
    @Column(name = "retired_flag", nullable = false)
    open var retiredFlag: Boolean,

    @Column(name = "monthly_income")
    open var monthlyIncome: BigDecimal? = null,
): EntityBean