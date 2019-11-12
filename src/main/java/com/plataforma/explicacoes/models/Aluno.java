package com.plataforma.explicacoes.models;


import lombok.Data;
import lombok.Generated;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity

public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int num;
    private String name;

    
   @ManyToMany(mappedBy = "Curso")
   private Set<Curso> curso = new HashSet<>();

    @OneToMany(mappedBy = "Aluno")
    private Set<Atendimento> atendimentos = new HashSet<>();

    public Aluno(String name, int num) {
        this.setName(name);
        this.setNum(num);
    }


}



