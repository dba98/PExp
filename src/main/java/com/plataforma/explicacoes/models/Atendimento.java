package com.plataforma.explicacoes.models;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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

    public Atendimento (LocalDate date, LocalTime hinicio, Professor professor, Aluno aluno, Cadeira cadeira){
        this.setDate(date);
        this.setDinicio(hinicio);
        this.setProfessor(professor);
        this.setCadeira(cadeira);
        this.setAluno(aluno);
    }

}

