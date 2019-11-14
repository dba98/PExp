package com.plataforma.explicacoes.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int num;

    @OneToMany(mappedBy = "atendimento")
    @JsonManagedReference
    private Set<Atendimento> atendimentos = new HashSet<>();

    @ManyToOne
    @JsonBackReference
    private Qualificacao grau;

    @OneToOne
    private Popularidade popularidade;


    public Professor(String name, int num) {
        this.setName(name);
        this.setNum(num);
    }
}
