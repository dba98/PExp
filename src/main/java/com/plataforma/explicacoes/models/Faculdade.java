package com.plataforma.explicacoes.models;

import lombok.Data;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Faculdade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    private Universidade universidade;

    @OneToMany
    private Set<Curso> curso= new HashSet<>();

    public Faculdade(String name, Universidade universidade) {
        this.name = name;
        this.universidade = universidade;
    }
}
