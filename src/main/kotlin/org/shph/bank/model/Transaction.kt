package org.shph.bank.model

import jakarta.persistence.*
import jakarta.validation.constraints.NotNull
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "transactions")
open class Transaction(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id", nullable = false)
    open var id: Long? = null,

    @NotNull
    @Column(name = "transaction_uuid", nullable = false)
    open var transactionUUID: UUID,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "account_id", nullable = true)
    open var account: Account,

    @NotNull
    @Column(name = "amount", nullable = false)
    open var amount: BigDecimal,

    @NotNull
    @Column(name = "transaction_timestamp", nullable = false)
    open var timestamp: LocalDateTime = LocalDateTime.now(),

    @NotNull
    @Column(name = "committed_flag", nullable = false)
    open var committed: Boolean = false
): EntityBean