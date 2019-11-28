package com.plataforma.explicacoes.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor

public class Atendimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Date dinicio;
    private Date dfim;

    @OneToOne
    @JsonIgnore
    private Cadeira cadeira;

    @ManyToOne
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonBackReference
    private Professor professor;

    @ManyToOne
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonBackReference
    private Aluno aluno;

    public Atendimento (Date dinicio, Date dfim, Professor professor, Aluno aluno, Cadeira cadeira){
        this.setDinicio(dinicio);
        this.setDfim(dfim);
        this.setProfessor(professor);
        this.setCadeira(cadeira);
        this.setAluno(aluno);
    }
}

