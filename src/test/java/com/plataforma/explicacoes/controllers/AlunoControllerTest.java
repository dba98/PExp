package com.plataforma.explicacoes.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.plataforma.explicacoes.models.Aluno;
import com.plataforma.explicacoes.repositories.AlunoRepo;
import com.plataforma.explicacoes.services.AlunoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AlunoController.class)
class AlunoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AlunoService alunoService;

    @MockBean
    private AlunoRepo alunoRepo;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllAluno() throws Exception {

        Aluno aluno = new Aluno("Ricardo",35249);
        aluno.setId(1L);

        when(this.alunoService.findById(1L)).thenReturn(Optional.of(aluno));

        String responseJson = this.mockMvc.perform(
                get("/aluno")
        ).andExpect(
                status().isOk()
        ).andReturn().getResponse().getContentAsString();


    }

    @Test
    void getAlunoById() throws Exception {

        Aluno aluno = new Aluno("Ricardo",35249);
        aluno.setId(1L);

        when(this.alunoService.findById(1L)).thenReturn(Optional.of(aluno));

        String responseJson = this.mockMvc.perform(
                get("/aluno/1")).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        Aluno responseAluno = this.objectMapper.readValue(responseJson, Aluno.class);
        assertEquals(aluno, responseAluno);

        this.mockMvc.perform(
                get("/client/2")
        ).andExpect(
                status().isNotFound()
        );


    }

    @Test
    void createAluno() throws Exception {

        Aluno aluno = new Aluno( "aluno1", 33333) ;

        String jsonRequest=this.objectMapper.writeValueAsString(aluno);

        when(this.alunoService.createAluno(aluno)).thenReturn(Optional.of(aluno));

        this.mockMvc.perform(
                post("/aluno").contentType(MediaType.APPLICATION_JSON).content(jsonRequest)
        ).andExpect(
                status().isOk()
        );

    }
}