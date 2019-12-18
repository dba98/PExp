package com.plataforma.explicacoes.models;


import com.fasterxml.jackson.annotation.JsonBackReference;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int num;
    private String name;

   @ManyToOne
   @EqualsAndHashCode.Exclude
   @ToString.Exclude
   @JsonBackReference(value = "curso_alunos")
//   @JsonIgnore
   private Curso curso;

    @OneToMany(mappedBy = "aluno",cascade = CascadeType.PERSIST)
    @JsonManagedReference(value = "alunos_atendimentos")
//    @JsonIgnore
    private Set<Atendimento> atendimentos = new HashSet<>();

    public Aluno(String name, int num) {
        this.setName(name);
        this.setNum(num);
    }

    public void associateCurso(Curso curso){

        this.setCurso(curso);
    }


    public void addAtendimento(Atendimento atendimento){
        this.atendimentos.add(atendimento);
    }



}



