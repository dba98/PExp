package com.plataforma.explicacoes.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Data
@Entity
@NoArgsConstructor
public class Idioma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany
    @JsonManagedReference
    private Set<Professor> professores = new HashSet<>();

    public Idioma (String name){
        this.setName(name);
    }

    public void addProfessor(Professor professor){
        this.professores.add(professor);
    }
}
