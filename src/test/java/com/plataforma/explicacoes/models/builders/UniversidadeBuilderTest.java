package com.plataforma.explicacoes.models.builders;

import com.plataforma.explicacoes.models.Faculdade;
import com.plataforma.explicacoes.models.Universidade;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class UniversidadeBuilderTest {

    @Test
    void build() {
        UniversidadeBuilder universidadeBuilder= new UniversidadeBuilder();
        universidadeBuilder.setName("UFP").setFaculdade(new HashSet<>());
        Universidade universidade= universidadeBuilder.build();

        assertNotNull(universidade.getName());
        assertNotNull(universidade.getFaculdade());
    }
}