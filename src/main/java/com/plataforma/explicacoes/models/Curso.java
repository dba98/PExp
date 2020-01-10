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
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private Integer codigo;

    @ManyToOne
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonBackReference(value = "faculdade_curso")
    private Faculdade faculdade;

    @OneToMany(mappedBy = "curso", cascade = CascadeType.PERSIST)
    @JsonManagedReference(value = "curso_cadeira")
    private Set<Cadeira> cadeiras = new HashSet<>();

    @OneToMany(mappedBy = "curso", cascade = CascadeType.PERSIST)
    @JsonManagedReference(value = "curso_alunos")
    private Set<Aluno> alunos = new HashSet<>();

    @OneToMany(mappedBy = "curso", cascade = CascadeType.PERSIST)
    @JsonManagedReference(value = "curso_professores")
    private Set<Professor> professores = new HashSet<>();


    public Curso(String nome, Integer codigo) {
        this.setNome(nome);
        this.setCodigo(codigo);
    }

    public Curso(Long id, String nome, Integer codigo, Faculdade faculdade, Set<Professor> professores, Set<Cadeira> cadeiras, Set<Aluno> alunos) {
        this.id = id;
        this.nome = nome;
        this.codigo = codigo;
        this.associateFaculdade(faculdade);
        for (Cadeira cadeira : cadeiras){
            this.addCadeira(cadeira);
        }
        for (Professor professor : professores) {
            this.addProfessor(professor);
        }
        for (Aluno aluno : alunos) {
            this.addAluno(aluno);
        }
    }
    public void addAluno(Aluno aluno) {
        this.alunos.add(aluno);
    }
    public void addCadeira(Cadeira cadeira) {
        this.cadeiras.add(cadeira);
    }
    public void addProfessor(Professor professor) {
        this.professores.add(professor);
    }
    public void associateFaculdade(Faculdade faculdade) {
        this.setFaculdade(faculdade);
    }

}
