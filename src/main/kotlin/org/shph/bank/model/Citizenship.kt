package org.shph.bank.model

import jakarta.persistence.*
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

@Entity
@Table(name = "citizenships")
class Citizenship (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "citizenship_id", nullable = false)
    var id: Long? = null,

    @Size(max = 250)
    @NotNull
    @Column(name = "citizenship_name", nullable = false, length = 250)
    var citizenshipName: String
): EntityBean