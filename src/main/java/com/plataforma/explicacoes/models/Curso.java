package com.plataforma.explicacoes.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private Integer codigo;

    @ManyToOne
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonBackReference
    private Faculdade faculdade;

    @OneToMany(mappedBy = "curso",cascade = CascadeType.PERSIST)
    @JsonManagedReference
    private Set<Cadeira> cadeiras= new HashSet<>();

    @OneToMany(mappedBy = "curso",cascade = CascadeType.PERSIST)
    @JsonManagedReference
    private Set<Aluno> alunos= new HashSet<>();

    public Curso(String nome, Integer codigo, Faculdade faculdade) {
        //this.setNome(nome);
        this(nome);
        this.setCodigo(codigo);
        this.setFaculdade(faculdade);
        faculdade.getCursos().add(this);
    }

    public Curso(String nome){
        this.setNome(nome);
    }


}
