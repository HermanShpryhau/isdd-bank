package org.shph.bank.controller.dto.assembler

interface DtoAssembler<E, D> {
    fun toEntity(dto: D): E

    fun toDto(entity: E): D
}