package org.shph.bank.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

@Entity
@Table(name = "currencies")
open class Currency(
    @Id
    @Column(name = "currency_id", nullable = false)
    open var id: Long? = null,

    @Size(max = 5)
    @NotNull
    @Column(name = "currency_name", nullable = false, length = 5)
    open var currencyName: String
): EntityBean