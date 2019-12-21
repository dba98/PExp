package com.plataforma.explicacoes.models.builders;

import com.plataforma.explicacoes.models.Curso;
import com.plataforma.explicacoes.models.Faculdade;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CursoBuilderTest {

    @Test
    void build() {
        CursoBuilder cursoBuilder= new CursoBuilder();
        cursoBuilder.setNome("Engenharia Informática").setId(1L).setCodigo(11111).setFaculdade(new Faculdade()).
                setCadeiras(new HashSet<>()).setAlunos(new HashSet<>()).build();
        Curso curso= cursoBuilder.build();

        assertNotNull(curso.getNome());
        assertNotNull(curso.getId());
        assertNotNull(curso.getFaculdade());
        assertNotNull(curso.getCadeiras());
        assertNotNull(curso.getAlunos());
        assertEquals(11111,curso.getCodigo());

    }
}