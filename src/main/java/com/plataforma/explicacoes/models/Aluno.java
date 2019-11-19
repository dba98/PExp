package com.plataforma.explicacoes.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    
   @ManyToOne
   @JsonBackReference
   private Curso curso;

    @OneToMany(mappedBy = "Aluno")
    @JsonManagedReference
    private Set<Atendimento> atendimentos = new HashSet<>();

    public Aluno(String name, int num, Curso curso) {
        this.setName(name);
        this.setNum(num);
        this.setCurso(curso);
        curso.getAlunos().add(this);
    }


}



