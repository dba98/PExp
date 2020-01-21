package com.plataforma.explicacoes.services;

import com.plataforma.explicacoes.models.Aluno;
import com.plataforma.explicacoes.models.Professor;
import com.plataforma.explicacoes.models.builders.AlunoBuilder;
import com.plataforma.explicacoes.models.builders.ProfessorBuilder;
import com.plataforma.explicacoes.repositories.ProfessorRepo;
import com.plataforma.explicacoes.services.filters.professor.FilterProfessorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = ProfessorService.class)
class ProfessorServiceTest {

    @Autowired
    private ProfessorService professorService;

    @MockBean
    private FilterProfessorService filterProfessorService;

    @MockBean
    private CursoService cursoService;

    @MockBean
    private ProfessorRepo professorRepo;

    @Test
    void findAll() {
        Professor professor = new ProfessorBuilder().setId(1L).build();
        Professor professor1 = new ProfessorBuilder().setId(2L).build();
        Set<Professor> professors = new HashSet<>();
        professors.add(professor);
        professors.add(professor1);
        when(this.professorRepo.findAll()).thenReturn(professors);
        assertEquals(2,professorService.findAll().size());
    }

    @Test
    void findById() {
        Professor professor = new ProfessorBuilder().setId(1L).build();
        when(this.professorRepo.findById(1L)).thenReturn(Optional.of(professor));
        assertNotNull(professorService.findById(1L));
        

    }

    @Test
    void createProfessor() {

    }

    @Test
    void findByNome() {

    }

    @Test
    void createHorario() {

    }

    @Test
    void filterProfessors() {
    }

    @Test
    void associateCurso() {
    }

    @Test
    void checkSobreposicao() {
    }
}