package com.plataforma.explicacoes.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.plataforma.explicacoes.models.Curso;
import com.plataforma.explicacoes.models.Faculdade;
import com.plataforma.explicacoes.services.CursoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = CursoController.class)
class CursoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CursoService cursoService;


    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createCurso() throws  Exception {

        Curso curso = new Curso( "CC");

        Faculdade faculdade = new Faculdade( "UFP");
        String jsonRequest=this.objectMapper.writeValueAsString(curso);

        when(this.cursoService.createCurso(curso, faculdade.getNome())).thenReturn(Optional.of(curso));

        this.mockMvc.perform(
                post("/curso/"+faculdade.getNome()).contentType(MediaType.APPLICATION_JSON_VALUE).content(jsonRequest)
        ).andExpect(
                status().isOk()
        );
    }
}