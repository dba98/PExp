package com.plataforma.explicacoes.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.plataforma.explicacoes.exceptions.ConflictedAtendimentoException;
import com.plataforma.explicacoes.models.*;
import com.plataforma.explicacoes.services.AtendimentoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = AtendimentoController.class)
class AtendimentoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AtendimentoService atendimentoService;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void createAtendimento() throws Exception {

        Aluno aluno = new Aluno( ) ;
        aluno.setId(1L);

        Aluno aluno2 = new Aluno("Ricado", 35249);
        Professor professor = new Professor();
        professor.setId(1L);
        Cadeira cadeira = new Cadeira( "Engenharia", 3);
        cadeira.setId(1L);
        Horario horario = new Horario(DayOfWeek.MONDAY, LocalTime.parse("09:00"), LocalTime.parse("18:00"));
        professor.getHorarios().add(horario);
        Atendimento atendimento = new Atendimento(LocalDate.parse("2019-12-16"), LocalTime.parse("10:00"),professor,aluno,cadeira);
        Atendimento atendimento2 = new Atendimento(LocalDate.parse("2019-12-16"), LocalTime.parse("10:00"),professor,aluno,cadeira);

        String jsonRequest1=this.objectMapper.writeValueAsString(atendimento);
        String jsonRequest2=this.objectMapper.writeValueAsString(atendimento2);

        when(this.atendimentoService.createAtendimento(atendimento)).thenReturn(Optional.of(atendimento));
        this.mockMvc.perform(
                post("/atendimento").contentType(MediaType.APPLICATION_JSON).content(jsonRequest1)
        ).andExpect(
                status().isOk()
        ).andReturn();

        when(this.atendimentoService.createAtendimento(atendimento2)).thenThrow(new ConflictedAtendimentoException());
        this.mockMvc.perform(
                post("/atendimento").contentType(MediaType.APPLICATION_JSON).content(jsonRequest2)
        ).andExpect(
                status().isBadRequest()
        );








    }
}