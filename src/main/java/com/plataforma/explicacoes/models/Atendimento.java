package com.plataforma.explicacoes.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity

public class Atendimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Date dinicio;
    private Date dfim;
    private Cadeira cadeira;

    @ManyToOne
    @JsonBackReference
    private Professor professor;

    @ManyToOne
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

