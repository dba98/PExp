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

    @OneToMany(mappedBy = "curso", cascade = CascadeType.PERSIST)
    @JsonManagedReference
    private Set<Cadeira> cadeiras = new HashSet<>();

    @OneToMany(mappedBy = "curso", cascade = CascadeType.PERSIST)
    @JsonManagedReference
    private Set<Aluno> alunos = new HashSet<>();

    public Curso(String nome, Integer codigo) {
        this.setNome(nome);
        this.setCodigo(codigo);
    }
    public void addAluno(Aluno aluno){
        this.alunos.add(aluno);
    }
    public void addCadeira(Cadeira cadeira){
        this.cadeiras.add(cadeira);
    }
    public void associateFaculdade(Faculdade faculdade){
        this.setFaculdade(faculdade);
    }
}
