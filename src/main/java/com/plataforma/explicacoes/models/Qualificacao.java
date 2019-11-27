package com.plataforma.explicacoes.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Qualificacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany
    @JsonManagedReference
    private Set<Professor> professores = new HashSet<>();

    public Qualificacao(String name) {
        this.setName(name);
    }

    public void addProfessor(Professor professor){
        this.professores.add(professor);
    }
}
