package com.example.petclinic.dto.request

import org.hibernate.validator.constraints.Length

data class CreateOwnerRequest(

        val name: String?,
        val surname: String?,

        @field:Length(min = 10, max = 10, message = "It must be 10 characters like this 5XXXXXXXXXX")
        val number: String?,
)
