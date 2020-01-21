package com.plataforma.explicacoes.services;

import ch.qos.logback.core.pattern.util.AlmostAsIsEscapeUtil;
import com.plataforma.explicacoes.models.Aluno;
import com.plataforma.explicacoes.models.builders.AlunoBuilder;
import com.plataforma.explicacoes.repositories.AlunoRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.when;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = AlunoService.class)
class AlunoServiceTest {

    @Autowired
    private AlunoService alunoService;

    @MockBean
    private AlunoRepo alunoRepo;


    @Test
    void findAll() {

        Aluno aluno = new AlunoBuilder().setId(1L).build();
        Aluno aluno1 = new AlunoBuilder().setId(2L).build();
        Set<Aluno> alunos = new HashSet<>();
        alunos.add(aluno);
        alunos.add(aluno1);
        when(this.alunoRepo.findAll()).thenReturn(alunos);
        assertEquals(2,alunoService.findAll().size());

    }

    @Test
    void findById() {
        Aluno aluno = new AlunoBuilder().setId(1L).build();
        when(this.alunoRepo.findById(1L)).thenReturn(Optional.of(aluno));
        assertNotNull(alunoService.findById(1L));
    }

    @Test
    void findByName() {
        Aluno aluno = new AlunoBuilder().setName("Diogo").build();
        when(this.alunoRepo.findByName("Diogo")).thenReturn(Optional.of(aluno));
        assertNotNull(alunoService.findByName("Diogo"));

    }

    @Test
    void createAluno() {
        Aluno aluno = new AlunoBuilder().setId(1L).setName("Diogo").build();
        when(this.alunoRepo.findByName("Diogo")).thenReturn(Optional.of(aluno));
        assertTrue(alunoService.createAluno(aluno).isEmpty());

        Aluno aluno1 = new Aluno("Catarina",2);
        when(this.alunoRepo.save(aluno1)).thenReturn(aluno1);
        assertEquals(Optional.of(aluno1),alunoService.createAluno(aluno1));

    }
}