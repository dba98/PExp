package com.plataforma.explicacoes.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Universidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int port;

    @OneToMany(mappedBy = "universidade" ,cascade = CascadeType.PERSIST)
    @JsonManagedReference(value = "universidade_faculdade")
    private Set<Faculdade> faculdade = new HashSet<>();

    public Universidade() {
        this.name = "UFP";
        this.port= 8081;
    }

    public void addFaculdade(Faculdade faculdade){
        this.faculdade.add(faculdade);
    }

}
