package com.example.petclinic.controller;

import com.example.petclinic.dto.PetDto;
import com.example.petclinic.dto.request.CreatePetRequest;
import com.example.petclinic.service.PetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/pet")
public class PetController {

    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @PostMapping
    public ResponseEntity<PetDto> createPet(@Valid @RequestBody CreatePetRequest request){
        return ResponseEntity.ok(petService.createPet(request));
    }
}
