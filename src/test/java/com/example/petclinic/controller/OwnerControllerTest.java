package com.example.petclinic.controller;

import com.example.petclinic.dto.OwnerDto;
import com.example.petclinic.dto.converter.OwnerDtoConverter;
import com.example.petclinic.dto.request.CreateOwnerRequest;
import com.example.petclinic.repository.OwnerRepository;
import com.example.petclinic.service.OwnerService;
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
class OwnerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private OwnerDtoConverter ownerDtoConverter;

    private OwnerService service = new OwnerService(ownerRepository, ownerDtoConverter);
    private ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void setup(){
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }

    @Test
    public void  testCreateOwner_shouldCreateOwnerAndReturnOwnerObject() throws Exception {
        CreateOwnerRequest request = new CreateOwnerRequest("name", "surname", "0000000000");

        this.mockMvc.perform(post("/v1/owner")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writer().withDefaultPrettyPrinter().writeValueAsString(request)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.name", is("name")))
                .andExpect(jsonPath("$.surname", is("surname")))
                .andExpect(jsonPath("$.number", is("0000000000")));

    }

}