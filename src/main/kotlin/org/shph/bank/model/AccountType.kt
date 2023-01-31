package org.shph.bank.model

import jakarta.persistence.*
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

@Entity
@Table(name = "account_type")
open class AccountType(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_type_id", nullable = false)
    open var id: Long? = null,

    @Size(max = 4)
    @NotNull
    @Column(name = "account_type_code", nullable = false, length = 4)
    open var accountTypeCode: String,

    @Size(max = 250)
    @NotNull
    @Column(name = "account_type_name", nullable = false, length = 250)
    open var accountTypeName: String,

    @NotNull
    @Column(name = "active_flag", nullable = false)
    open var activeFlag: Boolean
)