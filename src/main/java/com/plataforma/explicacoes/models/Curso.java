package com.plataforma.explicacoes.models;

import lombok.Data;
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
    private Faculdade faculdade;

    @OneToMany
    private Set<Cadeira> cadeira= new HashSet<>();
    @OneToMany
    private Set<Aluno> aluno= new HashSet<>();

    public Curso(String nome, Integer codigo, Faculdade faculdade) {
        this.nome = nome;
        this.codigo = codigo;
        this.faculdade = faculdade;
    }
}
