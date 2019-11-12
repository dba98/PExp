package com.plataforma.explicacoes.models;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity

public class Atendimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Date Dinicio;
    private Date Dfim;
    //professor
    //aluno
    //cadeira

    public Atendimento (Date dinicio, Date dfim){

        this.setDinicio(dinicio);
        this.setDfim(dfim);
    }
}

