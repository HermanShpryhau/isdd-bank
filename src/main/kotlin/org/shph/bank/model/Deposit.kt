package org.shph.bank.model

import jakarta.persistence.*
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import java.time.LocalDate

@Entity
@Table(name = "deposits")
open class Deposit(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "deposit_id", nullable = false)
    open var id: Long? = null,

    @Size(max = 250)
    @NotNull
    @Column(name = "contract_number", nullable = false, length = 250)
    open var contractNumber: String,

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "owner_id", nullable = false)
    open var owner: Client,

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "deposit_account_id", nullable = false)
    open var depositAccount: Account,

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "interest_account_id", nullable = false)
    open var interestAccount: Account,

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "deposit_type_id", nullable = false)
    open var depositType: DepositType,

    @NotNull
    @Column(name = "start_date", nullable = false)
    open var startDate: LocalDate,

    @NotNull
    @Column(name = "end_date", nullable = false)
    open var endDate: LocalDate,

    @NotNull
    @Column(name = "next_interest_pay_date", nullable = false)
    open var nextInterestPayDate: LocalDate,

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "currency_id", nullable = false)
    open var currency: Currency,

    @NotNull
    @Column(name = "active_flag", nullable = false)
    open var active: Boolean = true
): EntityBean