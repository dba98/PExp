package com.plataforma.explicacoes.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @ManyToMany(mappedBy = "cadeira")
    @JsonManagedReference
    private Set<Idioma> idiomas = new HashSet<>();

    @ManyToMany
    @JsonIgnore
    private Set<Cadeira> cadeiras = new HashSet<>();

    @ManyToOne
    @JsonBackReference
    private Qualificacao grau;

    @OneToOne
    private Popularidade popularidade;

    public Professor(String name, int num,Cadeira cadeira, Idioma idioma,Qualificacao grau) {
        this.setName(name);
        this.setNum(num);
        this.setGrau(grau);
        this.getIdiomas().add(idioma);
        this.getCadeiras().add(cadeira);
        cadeira.getProfessores().add(this);
        idioma.getProfessores().add(this);
        grau.getProfessor().add(this);

    }
}
