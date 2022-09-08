package com.example.petclinic.dto

data class PetDto(

        val id: String?,
        val age: Int?,
        val type: String?,
        val owner: PetOwnerDto?

)
