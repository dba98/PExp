package com.plataforma.explicacoes.models.builders;

import com.plataforma.explicacoes.models.Professor;
import com.plataforma.explicacoes.models.Qualificacao;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ProfessorBuilderTest {

    @Test
    void build() {

        ProfessorBuilder professorBuilder= new ProfessorBuilder();
        professorBuilder.setName("Alessandro Moreira").setId(1L).setNum(11111).
                setGrau(new Qualificacao()).setCadeiras(new HashSet<>()).setIdiomas(new HashSet<>());
        Professor professor= professorBuilder.build();

        assertNotNull(professor.getNome());
        assertNotNull(professor.getId());
        assertNotNull(professor.getGrau());
        assertNotNull(professor.getCadeiras());
        assertNotNull(professor.getIdiomas());
        assertEquals(11111,professor.getNum());

    }
}