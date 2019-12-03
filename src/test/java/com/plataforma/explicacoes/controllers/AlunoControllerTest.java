package com.plataforma.explicacoes.controllers;

import ch.qos.logback.core.net.server.Client;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.plataforma.explicacoes.models.Aluno;
import com.plataforma.explicacoes.repositories.AlunoRepo;
import com.plataforma.explicacoes.services.AlunoService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AlunoController.class)
class AlunoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AlunoService alunoRepo;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllAluno() {
    }

    @Test
    void getAlunoById() throws Exception {

        Aluno aluno = new Aluno("Ricardo",35249);
        aluno.setId(1L);

        when(this.alunoRepo.findById(1L)).thenReturn(Optional.of(aluno));

        String responseJson = this.mockMvc.perform(
                get("/aluno/1")
        ).andExpect(
                status().isOk()
        ).andReturn().getResponse().getContentAsString();

        Aluno responseAluno = this.objectMapper.readValue(responseJson, Aluno.class);
        assertEquals(aluno, responseAluno);

        this.mockMvc.perform(
                get("/client/2")
        ).andExpect(
                status().isNotFound()
        );


    }

    @Test
    void createAluno() {
    }
}