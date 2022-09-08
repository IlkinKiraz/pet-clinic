package com.example.petclinic.dto

import com.example.petclinic.dto.OwnerPetDto

data class OwnerDto(

        val id: String?,
        val name: String?,
        val surname: String?,
        val number: String?,
        val pets: Set<OwnerPetDto>
)
