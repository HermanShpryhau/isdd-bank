package org.shph.bank.model

import jakarta.persistence.*
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

@Entity
@Table(name = "cards")
open class Card(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_id", nullable = false)
    open var id: Long? = null,

    @Size(max = 16)
    @NotNull
    @Column(name = "card_number", nullable = false, length = 16)
    open var cardNumber: String,

    @Size(max = 4)
    @NotNull
    @Column(name = "pin", nullable = false, length = 4)
    open var pin: String,

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "owner_id", nullable = false)
    open var owner: Client,

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "account_id", nullable = false)
    open var account: Account
): EntityBean