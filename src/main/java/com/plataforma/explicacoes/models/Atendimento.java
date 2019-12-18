package com.plataforma.explicacoes.models;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@NoArgsConstructor

public class Atendimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDate date;
    private LocalTime dinicio;
    private LocalTime dfim;

    @OneToOne
    private Cadeira cadeira;

    @ManyToOne
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonBackReference(value = "professores_atendimentos")
    private Professor professor;

    @ManyToOne
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonBackReference(value = "alunos_atendimentos")
    private Aluno aluno;

    public Atendimento (LocalDate date, LocalTime hinicio, LocalTime hfim, Professor professor, Aluno aluno, Cadeira cadeira){
        this.setDate(date);
        this.setDinicio(hinicio);
        this.setDfim(hfim);
        this.setProfessor(professor);
        this.setCadeira(cadeira);
        this.setAluno(aluno);
    }

}

