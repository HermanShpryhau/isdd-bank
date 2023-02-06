package org.shph.bank.model

import jakarta.persistence.*
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import java.math.BigDecimal

@Entity
@Table(name = "accounts")
open class Account(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id", nullable = false)
    open var id: Long? = null,

    @Size(max = 13)
    @NotNull
    @Column(name = "account_number", nullable = false, length = 13)
    open var accountNumber: String,

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "account_type_id", nullable = false)
    open var accountType: AccountType,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_owner")
    open var accountOwner: Client?,

    @NotNull
    @Column(name = "account_balance", nullable = false)
    open var accountBalance: BigDecimal = BigDecimal.ZERO
): EntityBean