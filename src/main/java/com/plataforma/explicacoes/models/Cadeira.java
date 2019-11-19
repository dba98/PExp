package com.plataforma.explicacoes.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Cadeira {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer codigo;

    @ManyToOne()
    @JsonBackReference
    private Curso curso;

    @ManyToMany(mappedBy = "Cadeira")
    @JsonManagedReference
    private Set<Professor> professores=new HashSet<>();

    public Cadeira(String name, Integer codigo, Curso curso) {
        this.setName(name);
        this.setCodigo(codigo);
        this.setCurso(curso);
        curso.getCadeiras().add(this);

    }
}
