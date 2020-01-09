package com.plataforma.explicacoes.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    @ManyToMany(mappedBy = "cadeiras",cascade = CascadeType.PERSIST)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonBackReference(value = "professor_cadeira")
    private Set<Professor> professores = new HashSet<>();

    @ManyToOne
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonBackReference(value = "curso_cadeira")
    private Curso curso;

    public Cadeira(String name, Integer codigo) {
        this.setName(name);
        this.setCodigo(codigo);
    }

    public void associateCurso(Curso curso){
        this.setCurso(curso);
    }
    public void addProfessor(Professor professor){this.professores.add(professor);}
}
