package com.example.petclinic.dto.converter;

import com.example.petclinic.dto.OwnerDto;
import com.example.petclinic.dto.OwnersDto;
import com.example.petclinic.model.Owner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OwnerDtoConverter {

    private final OwnerPetsDtoConverter ownerPetsDtoConverter;

    public OwnerDtoConverter(OwnerPetsDtoConverter ownerPetsDtoConverter) {
        this.ownerPetsDtoConverter = ownerPetsDtoConverter;
    }

    public OwnerDto convertOneCustomer(Owner from){
        return new OwnerDto(
                from.getId(),
                from.getName(),
                from.getSurname(),
                from.getNumber(),
                from.getPets()
                        .stream()
                        .map(t -> ownerPetsDtoConverter.convert(t))
                        .collect(Collectors.toSet()));
    }

    public List<OwnersDto> convertAllCustomer(List<Owner> list){
        return list.stream()
                .map(t -> new OwnersDto(
                        t.getId(),
                        t.getName(),
                        t.getSurname(),
                        t.getNumber()
                )).collect(Collectors.toList());
    }

}
