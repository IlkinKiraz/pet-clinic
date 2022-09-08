package com.example.petclinic.dto.converter;

import com.example.petclinic.dto.OwnerPetDto;
import com.example.petclinic.dto.PetDto;
import com.example.petclinic.model.Pet;
import org.springframework.stereotype.Component;

@Component
public class OwnerPetsDtoConverter {

    public OwnerPetDto convert(Pet from){
        return new OwnerPetDto(
                from.getId(),
                from.getAge(),
                from.getType()
        );
    }

}
