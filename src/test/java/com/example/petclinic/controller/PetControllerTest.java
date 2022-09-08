package com.example.petclinic.controller;

import com.example.petclinic.dto.converter.PetDtoConverter;
import com.example.petclinic.dto.request.CreatePetRequest;
import com.example.petclinic.model.Owner;
import com.example.petclinic.repository.OwnerRepository;
import com.example.petclinic.repository.PetRepository;
import com.example.petclinic.service.OwnerService;
import com.example.petclinic.service.PetService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.*;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = {
        "server-port=0",
        "command.line.runner.enabled=false"
})
@RunWith(SpringRunner.class)
@DirtiesContext
class PetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private OwnerService ownerService;
    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private PetDtoConverter petDtoConverter;

    private PetService petService = new PetService(petRepository, ownerService, petDtoConverter);

    private ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void setup(){
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }

    @Test
    public void testCreatePet_whenOwnerIdExists_shouldCreatePetAndReturnPetDto() throws Exception {
        Owner owner = ownerRepository.save(new Owner("name", "surname", "000000000"));

        CreatePetRequest request = new CreatePetRequest(owner.getId(), 5, "type");

        this.mockMvc.perform(post("/v1/pet")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writer().withDefaultPrettyPrinter().writeValueAsString(request)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.age", is(5)))
                .andExpect(jsonPath("$.type", is("type")))
                .andExpect(jsonPath("$.owner.id", is(owner.getId())));

    }

}