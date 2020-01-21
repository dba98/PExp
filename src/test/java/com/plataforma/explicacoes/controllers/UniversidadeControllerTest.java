package com.plataforma.explicacoes.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.plataforma.explicacoes.models.Universidade;
import com.plataforma.explicacoes.services.UniversidadeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UniversidadeController.class)
class UniversidadeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UniversidadeService universidadeService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getUniversidade() throws Exception {

        Universidade universidade = new Universidade();
        when(this.universidadeService.getUniversidade()).thenReturn(universidade);
        String responseJson = this.mockMvc.perform(get("/universidade")).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        Universidade responseUniversidade = this.objectMapper.readValue(responseJson, Universidade.class);
        assertEquals(responseUniversidade, universidade);

    }

    @Test
    void sendUniversidade() {


    }
}