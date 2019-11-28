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
public class Universidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "universidade" ,cascade = CascadeType.PERSIST)
    @JsonManagedReference
    private Set<Faculdade> faculdade = new HashSet<>();

    public Universidade(String name) {
        this.name = name;
    }

    public void addFaculdade(Faculdade faculdade){
        this.faculdade.add(faculdade);
    }

}
