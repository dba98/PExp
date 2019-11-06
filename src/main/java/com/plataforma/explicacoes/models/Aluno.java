package com.plataforma.explicacoes.models;


import lombok.Data;
import lombok.Generated;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity

public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int num;
    private String name;
    // falta adicionar curso CURSO!
    // façta atendimentos

    public Aluno(String name, int num){
        this.setName(name);
        this.setNum(num);
    }

}



