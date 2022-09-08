package com.example.petclinic.model

import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

@Entity
data class Pet(

        @Id
        @GeneratedValue(generator = "UUID")
        @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
        val id: String?,

        val age: Int?,
        val type: String?,

        @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = [CascadeType.ALL])
        @JoinColumn(name = "owner_id", nullable = false)
        val owner: Owner


){

    constructor( age: Int, type: String, owner: Owner): this(
            "",
            age = age,
            type = type,
            owner = owner
    )

    override fun equals(other: Any?): Boolean {
                if (this === other) return true
                if (javaClass != other?.javaClass) return false

                other as Pet

                if (id != other.id) return false
                if (age != other.age) return false
                if (type != other.type) return false
                if (owner != other.owner) return false

                return true
    }

    override fun hashCode(): Int {
            var result = id?.hashCode() ?: 0
            result = 31 * result + (age ?: 0)
            result = 31 * result + (type?.hashCode() ?: 0)
            result = 31 * result + owner.hashCode()
            return result
    }


}
