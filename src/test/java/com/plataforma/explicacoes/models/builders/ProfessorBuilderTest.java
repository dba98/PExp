package com.plataforma.explicacoes.models.builders;

import com.plataforma.explicacoes.models.*;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class ProfessorBuilderTest {

    @Test
    void build() {

        ProfessorBuilder professorBuilder= new ProfessorBuilder();
        professorBuilder.setName("Alessandro Moreira").setId(1L).setNum(11111).
                setGrau(new Qualificacao()).setCurso(new Curso()).setIdiomas(new HashSet<>());
        Professor professor= professorBuilder.build();

        assertNotNull(professor.getName());
        assertNotNull(professor.getId());
        assertNotNull(professor.getGrau());
        assertNotNull(professor.getCurso());
        assertNotNull(professor.getIdiomas());
        assertEquals(11111,professor.getNum());

    }
}