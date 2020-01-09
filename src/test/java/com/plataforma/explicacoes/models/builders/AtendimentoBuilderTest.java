package com.plataforma.explicacoes.models.builders;

import com.plataforma.explicacoes.models.Aluno;
import com.plataforma.explicacoes.models.Atendimento;
import com.plataforma.explicacoes.models.Cadeira;
import com.plataforma.explicacoes.models.Professor;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class AtendimentoBuilderTest {

    @Test
    void build(){
        AtendimentoBuilder atendimentoBuilder= new AtendimentoBuilder();
        atendimentoBuilder.setId(1L).setDate(LocalDate.now()).setDinicio(LocalTime.of(10,00)).
                setDfim(LocalTime.of(11,00)).setAluno(new Aluno()).setProfessor(new Professor()).
                setCadeira(new Cadeira()).build();
        Atendimento atendimento= atendimentoBuilder.build();

        assertNotNull(atendimento.getDate());
        assertNotNull(atendimento.getDinicio());
        assertNotNull(atendimento.getAluno());
        assertNotNull(atendimento.getCadeira());
        assertNotNull(atendimento.getProfessor());
    }
}