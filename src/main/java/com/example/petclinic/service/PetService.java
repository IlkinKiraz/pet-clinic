package com.example.petclinic.service;

import com.example.petclinic.dto.PetDto;
import com.example.petclinic.dto.converter.PetDtoConverter;
import com.example.petclinic.dto.request.CreatePetRequest;
import com.example.petclinic.model.Owner;
import com.example.petclinic.model.Pet;
import com.example.petclinic.repository.PetRepository;
import org.springframework.stereotype.Service;

@Service
public class PetService {

    private final PetRepository petRepository;
    private final OwnerService ownerService;

    private final PetDtoConverter petDtoConverter;

    public PetService(PetRepository petRepository, OwnerService ownerService, PetDtoConverter petDtoConverter) {
        this.petRepository = petRepository;
        this.ownerService = ownerService;
        this.petDtoConverter = petDtoConverter;
    }


    public PetDto createPet(CreatePetRequest createPetRequest){
        Owner owner = ownerService.findOwnerById(createPetRequest.getOwnerId());

        Pet pet = new Pet(

                createPetRequest.getAge(),
                createPetRequest.getType(),
                owner

        );

        return petDtoConverter.convert(petRepository.save(pet));
    }

}
