package com.plataforma.explicacoes.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.plataforma.explicacoes.models.Cadeira;
import com.plataforma.explicacoes.repositories.CadeiraRepo;
import com.plataforma.explicacoes.services.CadeiraService;
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

@WebMvcTest(controllers = CadeiraController.class)
public class CadeiraControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CadeiraService cadeiraService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllCadeira() throws Exception {

        Cadeira cadeira = new Cadeira("Matemática",35249);
        cadeira.setId(1L);

        when(this.cadeiraService.findById(1L)).thenReturn(Optional.of(cadeira));

        this.mockMvc.perform(
                get("/cadeira")
        ).andExpect(
                status().isOk()
        ).andReturn().getResponse().getContentAsString();


    }

    @Test
    void getCadeiraById() throws Exception {

        Cadeira cadeira = new Cadeira("Matematica",35249);
        cadeira.setId(1L);

        when(this.cadeiraService.findById(1L)).thenReturn(Optional.of(cadeira));

        String responseJson = this.mockMvc.perform(
                get("/cadeira/1")
        ).andExpect(
                status().isOk()
        ).andReturn().getResponse().getContentAsString();

        Cadeira responseCadeira = this.objectMapper.readValue(responseJson, Cadeira.class);
        assertEquals(cadeira, responseCadeira);

        this.mockMvc.perform(
                get("/cadeira/2")
        ).andExpect(
                status().isNotFound()
        );


    }

    @Test
    void createCadeira() throws Exception {

        Cadeira cadeira = new Cadeira( "cadeira1", 33333) ;

        String jsonRequest=this.objectMapper.writeValueAsString(cadeira);

        when(this.cadeiraService.createCadeira(cadeira)).thenReturn(Optional.of(cadeira));

        this.mockMvc.perform(
                post("/cadeira").contentType(MediaType.APPLICATION_JSON).content(jsonRequest)
        ).andExpect(
                status().isOk()
        );

    }
}
