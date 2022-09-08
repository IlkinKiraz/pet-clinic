package com.example.petclinic.service;

import com.example.petclinic.dto.OwnerDto;
import com.example.petclinic.dto.OwnersDto;
import com.example.petclinic.dto.converter.OwnerDtoConverter;
import com.example.petclinic.dto.request.CreateOwnerRequest;
import com.example.petclinic.exception.OwnerNotFoundException;
import com.example.petclinic.model.Owner;
import com.example.petclinic.repository.OwnerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OwnerService {

    private final OwnerRepository ownerRepository;
    private final OwnerDtoConverter ownerDtoConverter;

    public OwnerService(OwnerRepository ownerRepository, OwnerDtoConverter ownerDtoConverter) {
        this.ownerRepository = ownerRepository;
        this.ownerDtoConverter = ownerDtoConverter;
    }

    public List<OwnersDto> getAllOwner(){
        return  ownerDtoConverter.convertAllCustomer(ownerRepository.findAll())
                .stream().collect(Collectors.toList());
    }

    protected Owner findOwnerById(String ownerId){
        return ownerRepository.findById(ownerId).orElseThrow(() -> new OwnerNotFoundException("Owner could not find by id: " + ownerId));
    }

    public OwnerDto getOwnerById(String ownerId){
        return ownerDtoConverter.convertOneCustomer(findOwnerById(ownerId));
    }

    public Owner createOwner(CreateOwnerRequest createOwnerRequest){
        Owner newOwner = new Owner(
                createOwnerRequest.getName(),
                createOwnerRequest.getSurname(),
                createOwnerRequest.getNumber()
        );

        return ownerRepository.save(newOwner);
    }

}