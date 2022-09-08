package com.example.petclinic.controller;

import com.example.petclinic.dto.OwnerDto;
import com.example.petclinic.dto.OwnersDto;
import com.example.petclinic.dto.request.CreateOwnerRequest;
import com.example.petclinic.model.Owner;
import com.example.petclinic.service.OwnerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/owner")
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }


    @GetMapping
    public ResponseEntity<List<OwnersDto>> getAllOwners(){
        return ResponseEntity.ok(ownerService.getAllOwner());
    }

    @GetMapping("/{ownerId}")
    public ResponseEntity<OwnerDto> getOwnerById(@PathVariable String ownerId){
        return ResponseEntity.ok(ownerService.getOwnerById(ownerId));
    }

    @PostMapping
    public Owner createOwner(@Valid @RequestBody CreateOwnerRequest request){
        return ResponseEntity.ok(ownerService.createOwner(request)).getBody();
    }

}
