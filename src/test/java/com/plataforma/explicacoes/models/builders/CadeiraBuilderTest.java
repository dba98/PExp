package com.plataforma.explicacoes.models.builders;

import com.plataforma.explicacoes.models.Cadeira;
import com.plataforma.explicacoes.models.Curso;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class CadeiraBuilderTest {

    @Test
    void build() {
        CadeiraBuilder cadeiraBuilder= new CadeiraBuilder();
        cadeiraBuilder.setName("Engenharia de Software").setCodigo(11111).setCurso(new Curso()).
                setProfessores(new HashSet<>()).build();
        Cadeira cadeira= cadeiraBuilder.build();

        assertNotNull(cadeira.getName());
        assertNotNull(cadeira.getCurso());
        assertNotNull(cadeira.getProfessores());
        assertNotNull(cadeira.getCodigo());
    }
}