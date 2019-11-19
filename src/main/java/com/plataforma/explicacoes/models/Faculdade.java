package com.plataforma.explicacoes.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JsonBackReference
    private Universidade universidade;

    @OneToMany(mappedBy = "faculdade", cascade = CascadeType.PERSIST)
    @JsonManagedReference
    private Set<Curso> cursos= new HashSet<>();

    public Faculdade(String name, Universidade universidade) {
        this.setName(name);
        this.setUniversidade(universidade);
        universidade.getFaculdade().add(this);
    }
}
