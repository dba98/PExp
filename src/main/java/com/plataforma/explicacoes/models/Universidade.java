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

    private static String name;
    private static int port;

    private static Universidade singelton_instance = null;

    @OneToMany(mappedBy = "universidade" ,cascade = CascadeType.PERSIST)
    @JsonManagedReference(value = "universidade_faculdade")
    private Set<Faculdade> faculdade = new HashSet<>();

    private static Universidade getUniversidade() {

        if(singelton_instance == null){
            singelton_instance = new Universidade();
        }

        return singelton_instance;
    }

    public Universidade (){

          name = "FEUP";
          port= 8082;

    }

    public void addFaculdade(Faculdade faculdade){
        this.faculdade.add(faculdade);
    }

}
