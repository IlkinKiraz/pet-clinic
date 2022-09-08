package com.example.petclinic.dto.converter;

import com.example.petclinic.dto.PetDto;
import com.example.petclinic.model.Pet;
import org.springframework.stereotype.Component;

@Component
public class PetDtoConverter {

    private final PetOwnerDtoConverter petOwnerDtoConverter;

    public PetDtoConverter(PetOwnerDtoConverter petOwnerDtoConverter) {
        this.petOwnerDtoConverter = petOwnerDtoConverter;
    }


    public PetDto convert(Pet from){
        return new PetDto(
                from.getId(),
                from.getAge(),
                from.getType(),
                petOwnerDtoConverter.convertToPetOwner(from.getOwner())
        );
    }



}
