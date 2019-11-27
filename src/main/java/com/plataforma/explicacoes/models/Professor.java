package com.plataforma.explicacoes.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

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

    @OneToMany(mappedBy = "professor")
    @JsonManagedReference
    private Set<Atendimento> atendimentos = new HashSet<>();

    @ManyToMany
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonBackReference
    private Set<Idioma> idiomas = new HashSet<>();

    @ManyToMany
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonBackReference
    private Set<Cadeira> cadeiras = new HashSet<>();

    @ManyToOne
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonBackReference
    private Qualificacao grau;

    public Professor(String name, int num) {
        this.setName(name);
        this.setNum(num);
    }

    public void addCadeira(Cadeira cadeira){
        this.cadeiras.add(cadeira);
    }
    public void addidioma(Idioma idioma){
        this.idiomas.add(idioma);
    }
    public void associateQualificacao(Qualificacao grau){
        this.setGrau(grau);
    }


}
