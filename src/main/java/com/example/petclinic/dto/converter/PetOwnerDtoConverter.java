package com.example.petclinic.dto.converter;

import com.example.petclinic.dto.PetOwnerDto;
import com.example.petclinic.model.Owner;
import org.springframework.stereotype.Component;

@Component
public class PetOwnerDtoConverter {


    public PetOwnerDto convertToPetOwner(Owner from){
        if (from == null){
            return new PetOwnerDto("", "", "", "");
        }

        return new PetOwnerDto(from.getId(), from.getName(), from.getSurname(), from.getNumber());
    }
}
