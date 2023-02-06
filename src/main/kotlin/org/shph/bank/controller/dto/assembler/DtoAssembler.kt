package org.shph.bank.controller.dto.assembler

import org.shph.bank.controller.dto.EntityDto
import org.shph.bank.model.EntityBean

interface DtoAssembler<E: EntityBean, D: EntityDto> {
    fun toEntity(dto: D): E

    fun toDto(entity: E): D
}