package com.plataforma.explicacoes.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;


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
    @JsonManagedReference(value = "universidade_faculdade")
    private Set<Faculdade> faculdade = new HashSet<>();

    public Universidade(String name, Set<Faculdade> faculdade) {
        this.name = name;
        for(Faculdade facculdade: faculdade)
            this.addFaculdade(facculdade);
    }

    public void addFaculdade(Faculdade faculdade){
        this.faculdade.add(faculdade);
    }

}
