package com.plataforma.explicacoes.models;


import com.fasterxml.jackson.annotation.JsonBackReference;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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

    public Aluno(int num, String name, Curso curso, Set<Atendimento> atendimentos) {
        this.num = num;
        this.name = name;
        this.curso = curso;
        for(Atendimento atendimento: atendimentos)
            this.addAtendimento(atendimento);
    }

    public void associateCurso(Curso curso){

        this.setCurso(curso);
    }


    public void addAtendimento(Atendimento atendimento){
        this.atendimentos.add(atendimento);
    }



}



