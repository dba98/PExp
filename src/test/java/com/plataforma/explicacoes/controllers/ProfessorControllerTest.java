package com.plataforma.explicacoes.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.plataforma.explicacoes.exceptions.ProfessorDoesNotExistException;
import com.plataforma.explicacoes.models.*;
import com.plataforma.explicacoes.models.builders.ProfessorBuilder;
import com.plataforma.explicacoes.services.ProfessorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.NestedServletException;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ProfessorController.class)
class ProfessorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProfessorService professorService;

    @Autowired
    private ObjectMapper objectMapper;

    /*
    @Test
    private Set<Professor> getAllProfessores() {
        Set<Professor> professores = new HashSet<>();
        for (Professor p1 : this.professorService.findAll()) {
            professores.add(p1);
        }
        return professores;
    }
    */


    @Test
    void getProfessorByName() throws Exception {
        Professor professor = new ProfessorBuilder().setId(1L).setName("Alessandro").setNum(35234).setGrau(new Qualificacao("Mestre", 2)).addCadeira(new Cadeira("Matematica", 123)).addIdioma(new Idioma("Portugues")).addHorario(new Horario(DayOfWeek.MONDAY, LocalTime.of(10, 0), LocalTime.of(12, 0))).build();
        when(this.professorService.findByNome("Alessandro")).thenReturn(Optional.of(professor));
        String responseJson = this.mockMvc.perform(get("/professor/Alessandro")).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        Professor responseProfessor = this.objectMapper.readValue(responseJson, Professor.class);
        assertEquals(professor, responseProfessor);
        assertThrows(NestedServletException.class, () -> this.mockMvc.perform(get("/professor/AlessandroMoreira")).andExpect(status().isNotFound()));
    }

    @Test
    void createProfessor() throws Exception {
        Professor professor = new ProfessorBuilder().setId(1L).setName("Alessandro Moreira").setNum(35234).setGrau(new Qualificacao("Mestre", 2)).addCadeira(new Cadeira("Matematica", 123)).addIdioma(new Idioma("Portugues")).addHorario(new Horario(DayOfWeek.MONDAY, LocalTime.of(10, 0), LocalTime.of(12, 0))).build();
        String jsonRequest = this.objectMapper.writeValueAsString(professor);
        when(this.professorService.createProfessor(professor)).thenReturn(Optional.of(professor));
        this.mockMvc.perform(post("/professor").contentType(MediaType.APPLICATION_JSON).content(jsonRequest)).andExpect(status().isOk());

    }

    @Test
    void createHorario() throws ProfessorDoesNotExistException, Exception {

        Professor professor = new Professor("Alessandro", 3234);
        Horario horario= new Horario(DayOfWeek.MONDAY, LocalTime.of(10, 0), LocalTime.of(12, 0));
        professor.addHorario(horario);
        String jsonRequest = this.objectMapper.writeValueAsString(professor);
        when(this.professorService.createHorario(professor)).thenReturn(Optional.of(professor));

        String responseJson = this.mockMvc.perform(put("/professor").contentType(MediaType.APPLICATION_JSON).content(jsonRequest)).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        Professor responseProfessor = this.objectMapper.readValue(responseJson, Professor.class);

        assertTrue(responseProfessor.getHorarios().contains(horario));
    }


    @Test
    void searchProfessors() {
    }

    @Test
    void associateCurso() throws Exception{
        Professor professor = new Professor("Alessandro", 3234);
        Curso curso = new Curso("Redes");

        String jsonRequest = this.objectMapper.writeValueAsString(professor);
        when(this.professorService.associateCurso(professor,curso.getNome())).thenReturn(Optional.of(professor));
        String responseJson = this.mockMvc.perform(put("/professor/"+curso.getNome()).contentType(MediaType.APPLICATION_JSON).content(jsonRequest)).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        Professor responseProfessor = this.objectMapper.readValue(responseJson, Professor.class);
        System.out.println(responseProfessor);
        assertEquals(responseProfessor.getCurso(), curso);

       this.mockMvc.perform(put("/professor/R").contentType(MediaType.APPLICATION_JSON).content(jsonRequest)).andExpect(status().isBadRequest());







    }
}