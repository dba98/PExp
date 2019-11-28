package com.plataforma.explicacoes.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
public class Faculdade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonBackReference
    private Universidade universidade;

    @OneToMany(mappedBy = "faculdade", cascade = CascadeType.PERSIST)
    @JsonManagedReference
    private Set<Curso> cursos= new HashSet<>();

    public Faculdade(String name, Universidade universidade) {
        this.setName(name);
        this.setUniversidade(universidade);

    }

    public void addCurso(Curso curso){
        curso.setFaculdade(this);
        this.cursos.add(curso);

    }
}
