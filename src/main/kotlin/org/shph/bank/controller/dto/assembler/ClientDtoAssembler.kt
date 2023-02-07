package org.shph.bank.controller.dto.assembler

import org.shph.bank.controller.dto.ClientEntityDto
import org.shph.bank.model.Address
import org.shph.bank.model.Client
import org.shph.bank.model.Disability
import org.shph.bank.repository.*
import org.springframework.stereotype.Component

@Component
class ClientDtoAssembler(
    val addressRepository: AddressRepository,
    val citizenshipRepository: CitizenshipRepository,
    val cityRepository: CityRepository,
    val disabilityRepository: DisabilityRepository,
    val maritalStatusRepository: MaritalStatusRepository
): DtoAssembler<Client, ClientEntityDto> {
    override fun toEntity(dto: ClientEntityDto): Client {
        val residentialAddressCity = cityRepository.findById(dto.residentialAddressCityId).orElseThrow()
        val residentialAddress = addressRepository.save(Address(city = residentialAddressCity, addressText = dto.residentialAddress))

        val registrationAddressCity = cityRepository.findById(dto.registrationAddressCityId).orElseThrow()
        val registrationAddress = addressRepository.save(Address(city = registrationAddressCity, addressText = dto.registrationAddress))

        val citizenship = citizenshipRepository.findById(dto.citizenshipId).orElseThrow()

        val maritalStatus = maritalStatusRepository.findById(dto.maritalStatusId).orElseThrow()

        val disability: Disability? = dto.disabilityId?.let { disabilityRepository.findById(it).orElse(null) }

        return Client(
            id = dto.id,
            firstName = dto.firstName,
            lastName = dto.lastName,
            middleName = dto.middleName,
            dateOfBirth = dto.dateOfBirth,
            sex = dto.sex,
            passportSeries = dto.passportSeries,
            passportNumber = dto.passportNumber,
            passportIdNumber = dto.passportIdNumber,
            passportIssuedBy = dto.passportIssuedBy,
            passportIssueDate = dto.passportIssueDate,
            placeOfBirth = dto.placeOfBirth,
            registrationAddress = registrationAddress,
            residentialAddress = residentialAddress,
            personalPhoneNumber = dto.personalPhoneNumber,
            homePhoneNumber = dto.homePhoneNumber,
            email = dto.email,
            placeOfWork = dto.placeOfWork,
            positionAtWork = dto.positionAtWork,
            citizenship = citizenship,
            maritalStatus = maritalStatus,
            disability = disability,
            retiredFlag = dto.retiredFlag,
            monthlyIncome = dto.monthlyIncome
        )
    }

    override fun toDto(entity: Client): ClientEntityDto {
        return ClientEntityDto(
            id = entity.id,
            lastName = entity.lastName,
            firstName = entity.firstName,
            middleName = entity.middleName,
            dateOfBirth = entity.dateOfBirth,
            sex = entity.sex,
            passportSeries = entity.passportSeries,
            passportNumber = entity.passportNumber,
            passportIdNumber = entity.passportIdNumber,
            passportIssuedBy = entity.passportIssuedBy,
            passportIssueDate = entity.passportIssueDate,
            placeOfBirth = entity.placeOfBirth,
            residentialAddressCityId = entity.residentialAddress.city.id!!,
            residentialAddress = entity.residentialAddress.addressText,
            registrationAddressCityId = entity.registrationAddress.city.id!!,
            registrationAddress = entity.registrationAddress.addressText,
            personalPhoneNumber = entity.personalPhoneNumber,
            homePhoneNumber = entity.homePhoneNumber,
            email = entity.email,
            placeOfWork = entity.placeOfWork,
            positionAtWork = entity.positionAtWork,
            citizenshipId = entity.citizenship.id!!,
            maritalStatusId = entity.maritalStatus.id!!,
            disabilityId = entity.disability?.id,
            retiredFlag = entity.retiredFlag,
            monthlyIncome = entity.monthlyIncome
        )
    }
}