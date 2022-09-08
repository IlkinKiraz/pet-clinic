package com.example.petclinic.service;

import com.example.petclinic.dto.OwnerDto;
import com.example.petclinic.dto.OwnersDto;
import com.example.petclinic.dto.converter.OwnerDtoConverter;
import com.example.petclinic.exception.OwnerNotFoundException;
import com.example.petclinic.model.Owner;
import com.example.petclinic.repository.OwnerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class OwnerServiceTest {

    private OwnerService service;
    private OwnerRepository repository;
    private OwnerDtoConverter converter;

    @BeforeEach
    public void setUp(){
        repository = mock(OwnerRepository.class);
        converter = mock(OwnerDtoConverter.class);
        service = new OwnerService(repository, converter);
    }

    @Test
    public void testGetAllOwnerId_whenOwnerIdExist_shouldReturnOwner(){
        Owner owner = new Owner("id", "name", "surname", "phoneNumber", Set.of());
        OwnersDto ownersDto = new OwnersDto("id", "name", "surname", "phoneNumber");

        Mockito.when(repository.findAll()).thenReturn(List.of(owner));
        Mockito.when(converter.convertAllCustomer( repository.findAll() )).thenReturn(List.of(ownersDto));

        OwnersDto result = service.getAllOwner().get(0);
        assertEquals(result.getId(), repository.findAll().get(0).getId());
    }

    @Test
    public void testFindByOwnerId_whenOwnerIdExist_shouldReturnOwner(){
        Owner owner = new Owner("id", "name", "surname", "phoneNumber", Set.of());

        Mockito.when(repository.findById("id")).thenReturn(Optional.of(owner));

        Owner result = service.findOwnerById("id");
        assertEquals(result, owner);
    }

    @Test
    public void testFindByOwnerId_whenOwnerIdExist_shouldReturnOwnerNotFoundException(){
        Mockito.when(repository.findById("id")).thenReturn(Optional.empty());

        assertThrows(OwnerNotFoundException.class, () -> service.findOwnerById("id"));
    }

    @Test
    public void testGetByOwnerId_whenOwnerIdExist_shouldReturnOwner(){
        Owner owner = new Owner("id", "name", "surname", "phoneNumber", Set.of());
        OwnerDto ownerDto = new OwnerDto("id", "name", "surname", "phoneNumber", Set.of());

        Mockito.when(repository.findById("id")).thenReturn(Optional.of(owner));
        Mockito.when(converter.convertOneCustomer(owner)).thenReturn(ownerDto);

        OwnerDto result = service.getOwnerById("id");
        assertEquals(result, ownerDto);
    }

    @Test
    public void testGetByOwnerId_whenOwnerIdExist_shouldReturnOwnerNotFoundException(){
        Mockito.when(repository.findById("id")).thenReturn(Optional.empty());

        assertThrows(OwnerNotFoundException.class, () -> service.getOwnerById("id"));

        Mockito.verifyNoInteractions(converter);

    }

}