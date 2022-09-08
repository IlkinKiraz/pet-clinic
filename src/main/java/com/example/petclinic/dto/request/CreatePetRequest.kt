package com.example.petclinic.dto.request

import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank

data class CreatePetRequest(

        @field:NotBlank
        val ownerId: String,

        @field:Min(0)
        val age: Int,
        val type: String

)
