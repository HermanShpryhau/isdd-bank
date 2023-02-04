package org.shph.bank.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import java.math.BigDecimal

@Entity
@Table(name = "deposit_types")
open class DepositType(
    @Id
    @Column(name = "deposit_type_id", nullable = false)
    open var id: Long? = null,

    @Size(max = 250)
    @NotNull
    @Column(name = "deposit_type_name", nullable = false, length = 250)
    open var depositTypeName: String,

    @NotNull
    @Column(name = "interest", nullable = false)
    open var interest: BigDecimal
): EntityBean