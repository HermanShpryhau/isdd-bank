package org.shph.bank.model

import jakarta.persistence.*
import jakarta.validation.constraints.NotNull
import java.math.BigDecimal
import java.time.LocalDate
import java.util.*

@Entity
@Table(name = "credits")
open class Credit (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "credit_id", nullable = false)
    open var id: Long? = null,

    @NotNull
    @Column(name = "contract_number", nullable = false)
    open var contractNumber: UUID,

    @NotNull
    @Column(name = "initial_amount", nullable = false)
    open var initialAmount: BigDecimal,

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "owner_id", nullable = false)
    open var owner: Client,

    @NotNull
    @Column(name = "start_date", nullable = false)
    open var startDate: LocalDate,

    @Column(name = "end_date")
    open var endDate: LocalDate,

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "currency_id", nullable = false)
    open var currency: Currency,

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "credit_type_id", nullable = false)
    open var creditType: DepositType,

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "credit_account_id", nullable = false)
    open var creditAccount: Account,

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "interest_account_id", nullable = false)
    open var interestAccount: Account
): EntityBean