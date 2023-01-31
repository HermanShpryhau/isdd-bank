package org.shph.bank.model

import jakarta.persistence.*
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

@Entity
@Table(name = "disabilities")
open class Disability(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "disability_id", nullable = false)
    open var id: Long? = null,

    @Size(max = 250)
    @NotNull
    @Column(name = "disablity_name", nullable = false, length = 250)
    open var disabilityName: String? = null
): EntityBean