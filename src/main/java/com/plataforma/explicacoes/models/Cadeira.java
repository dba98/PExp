package com.plataforma.explicacoes.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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

    public Cadeira(String name, Integer codigo, Set<Professor> professores, Curso curso) {
        this.name = name;
        this.codigo = codigo;
        this.professores = professores;
        this.curso = curso;
    }

    public void associateCurso(Curso curso){
        this.setCurso(curso);
    }
    public void addProfessor(Professor professor){this.professores.add(professor);}
}
