package com.plataforma.explicacoes.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties(value = {"professores"},allowSetters = true)
public class Cadeira {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer codigo;

    @ManyToOne
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonBackReference(value = "curso_cadeira")
    private Curso curso;

    @ManyToMany(mappedBy = "cadeiras",cascade = CascadeType.PERSIST)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonBackReference(value = "professor_cadeira")
    //@JsonIgnore
    private Set<Professor> professores=new HashSet<>();

    public Cadeira(String name, Integer codigo) {
        this.setName(name);
        this.setCodigo(codigo);
    }

    public Cadeira(String name, Integer codigo, Curso curso, Set<Professor> professores) {
        this.name = name;
        this.codigo = codigo;
        this.curso = curso;
        this.professores = professores;
    }

    public void addProfessor(Professor professor){
        this.professores.add(professor);
    }
    public void associateCurso(Curso curso){
        this.setCurso(curso);
    }
}
