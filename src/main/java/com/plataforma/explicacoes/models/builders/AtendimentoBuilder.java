package com.plataforma.explicacoes.models.builders;

import com.plataforma.explicacoes.models.Aluno;
import com.plataforma.explicacoes.models.Atendimento;
import com.plataforma.explicacoes.models.Cadeira;
import com.plataforma.explicacoes.models.Professor;

import java.time.LocalDate;
import java.time.LocalTime;

public class AtendimentoBuilder {

    private long id;
    private LocalDate date;
    private LocalTime dinicio;
    private LocalTime dfim;
    private Cadeira cadeira;
    private Professor professor;
    private Aluno aluno;

    public AtendimentoBuilder setId(long id) {
        this.id = id;
        return this;
    }

    public AtendimentoBuilder setDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public AtendimentoBuilder setDinicio(LocalTime dinicio) {
        this.dinicio = dinicio;
        return this;
    }

    public AtendimentoBuilder setDfim(LocalTime dfim) {
        this.dfim = dfim;
        return this;
    }

    public AtendimentoBuilder setCadeira(Cadeira cadeira) {
        this.cadeira = cadeira;
        return this;
    }

    public AtendimentoBuilder setProfessor(Professor professor) {
        this.professor = professor;
        return this;
    }

    public AtendimentoBuilder setAluno(Aluno aluno) {
        this.aluno = aluno;
        return this;
    }

    public Atendimento build(){ return new Atendimento(date, dinicio, dfim, professor, aluno, cadeira);}
}
