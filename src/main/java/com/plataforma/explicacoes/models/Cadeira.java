package com.plataforma.explicacoes.models;

import lombok.Data;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Cadeira {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer codigo;

    /*@ManyToOne
    private Curso curso;*/

    @ManyToMany
    private Set<Professor> professor=new HashSet<>();

    public Cadeira(String name, Integer codigo) {
        this.name = name;
        this.codigo = codigo;
    }
}
