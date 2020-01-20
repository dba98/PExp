package com.plataforma.explicacoes.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
public class Faculdade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @ManyToOne
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonBackReference(value = "universidade_faculdade")
    private Universidade universidade;

    @OneToMany(mappedBy = "faculdade", cascade = CascadeType.PERSIST)
    @JsonManagedReference(value = "faculdade_curso")
    private Set<Curso> cursos= new HashSet<>();

    public Faculdade(String nome) {
        this.setNome(nome);
    }

    public void addCurso(Curso curso){
        curso.setFaculdade(this);
        this.cursos.add(curso);

    }
}
