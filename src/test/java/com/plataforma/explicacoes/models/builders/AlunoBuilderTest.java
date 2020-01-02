package com.plataforma.explicacoes.models.builders;

import com.plataforma.explicacoes.models.Aluno;
import com.plataforma.explicacoes.models.Curso;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class AlunoBuilderTest {

    @Test
    void build() {
        AlunoBuilder alunoBuilder= new AlunoBuilder();
        alunoBuilder.setNum(35367).setName("Catarina").setCurso(new Curso()).setAtendimentos(new HashSet<>());
        Aluno aluno= alunoBuilder.build();

        assertNotNull(aluno.getName());
        assertNotNull(aluno.getCurso());
        assertNotNull(aluno.getAtendimentos());
        assertEquals(35367,aluno.getNum());
    }
}