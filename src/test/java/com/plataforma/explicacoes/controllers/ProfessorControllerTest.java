package com.plataforma.explicacoes.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.plataforma.explicacoes.models.Professor;
import com.plataforma.explicacoes.services.AlunoService;
import com.plataforma.explicacoes.services.ProfessorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashSet;
import java.util.Set;

@WebMvcTest(controllers = ProfessorController.class)
class ProfessorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProfessorService professorService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    private Set<Professor> getAllProfessores() {
        Set<Professor> professores = new HashSet<>();
        for (Professor p1 : this.professorService.findAll()){
            professores.add(p1);
        }
        return professores;
    }

    @Test
    void getProfessorById() {
    }

    @Test
    void createProfessor() {
    }

    @Test
    void createHorario() {
    }
}