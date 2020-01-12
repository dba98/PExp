package com.plataforma.explicacoes.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.plataforma.explicacoes.models.Faculdade;
import com.plataforma.explicacoes.models.Universidade;
import com.plataforma.explicacoes.services.FaculdadeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
        Universidade uni = new Universidade();
        Faculdade faculdade = new Faculdade("Faculdade Medicina Dentaria",uni);

        String jsonRequest=this.objectMapper.writeValueAsString(faculdade);

        when(this.faculdadeService.createFaculdade(faculdade)).thenReturn(Optional.of(faculdade));

        this.mockMvc.perform(
                post("/faculdade").contentType(MediaType.APPLICATION_JSON_VALUE).content(jsonRequest)
        ).andExpect(
                status().isOk()
        );
    }

    @Test
    void getAllFaculdade() throws Exception {

        Universidade uni = new Universidade();
        Faculdade faculdade = new Faculdade("Faculdade Medicina Dentaria",uni);
        faculdade.setId(1L);

        when(this.faculdadeService.findById(1L)).thenReturn(Optional.of(faculdade));

        this.mockMvc.perform(
                get("/faculdade")
        ).andExpect(
                status().isOk()
        ).andReturn().getResponse().getContentAsString();


    }

    @Test
    void getFaculdadeById() throws Exception {

        Universidade uni = new Universidade();
        Faculdade faculdade = new Faculdade("Faculdade Medicina Dentaria",uni);
        faculdade.setId(1L);

        when(this.faculdadeService.findById(1L)).thenReturn(Optional.of(faculdade));

        String responseJson = this.mockMvc.perform(
                get("/faculdade/1")
        ).andExpect(
                status().isOk()
        ).andReturn().getResponse().getContentAsString();

        Faculdade responseFaculdade = this.objectMapper.readValue(responseJson, Faculdade.class);
        assertEquals(faculdade, responseFaculdade);

        this.mockMvc.perform(
                get("/faculdade/2")
        ).andExpect(
                status().isNotFound()
        );
    }
}