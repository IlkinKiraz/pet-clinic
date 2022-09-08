package com.example.petclinic.model

import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

@Entity
data class Owner(

        @Id
        @GeneratedValue(generator = "UUID")
        @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
        val id: String?,

        val name: String?,
        val surname: String?,
        val number: String?,


        @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
        val pets: Set<Pet>



){

        constructor(name: String, surname: String, number: String) : this(
                "",
                name,
                surname,
                number,
                HashSet()
        )

        override fun equals(other: Any?): Boolean {
                if (this === other) return true
                if (javaClass != other?.javaClass) return false

                other as Owner

                if (id != other.id) return false
                if (name != other.name) return false
                if (surname != other.surname) return false
                if (number != other.number) return false
                if (pets != other.pets) return false

                return true
        }

        override fun hashCode(): Int {
                var result = id?.hashCode() ?: 0
                result = 31 * result + (name?.hashCode() ?: 0)
                result = 31 * result + (surname?.hashCode() ?: 0)
                result = 31 * result + (number?.hashCode() ?: 0)
                return result
        }


}
