package com.plataforma.explicacoes.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.plataforma.explicacoes.models.Faculdade;
import com.plataforma.explicacoes.models.Universidade;
import com.plataforma.explicacoes.repositories.FaculdadeRepo;
import com.plataforma.explicacoes.services.FaculdadeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = FaculdadeController.class)
class FaculdadeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FaculdadeService faculdadeService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createFaculdade() throws Exception {
        Universidade uni = new Universidade("UFP");
        Faculdade faculdade = new Faculdade("Faculdade Medicina Dentaria",uni);
        //faculdade.setId(1L);
        String jsonRequest=this.objectMapper.writeValueAsString(faculdade);

        System.out.println(jsonRequest);

        this.mockMvc.perform(
                post("/faculdade").contentType(MediaType.APPLICATION_JSON_VALUE).content(jsonRequest)
        ).andExpect(
                status().isOk()
        );

    }
}