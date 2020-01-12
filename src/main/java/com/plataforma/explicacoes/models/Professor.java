package com.plataforma.explicacoes.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.ManyToMany;
import javax.persistence.CascadeType;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
//@JsonIgnoreProperties(value = {"cadeiras"},allowSetters = true)
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private int num;

    @OneToMany(mappedBy = "professor", cascade = CascadeType.PERSIST)
    @JsonManagedReference(value = "professores_atendimentos")
    private Set<Atendimento> atendimentos = new HashSet<>();

    @ManyToMany
    //@EqualsAndHashCode.Exclude
    //@ToString.Exclude
//    @JsonManagedReference(value = "professor_idioma")
    private Set<Idioma> idiomas = new HashSet<>();

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    //@EqualsAndHashCode.Exclude
    //@ToString.Exclude
    @JsonManagedReference(value = "professores_horarios")
    // @JsonIgnore
    private Set<Horario> horarios = new HashSet<>();

    @ManyToMany
    @JsonManagedReference(value = "professor_cadeira")
    @JsonIgnore
    private Set<Cadeira> cadeiras = new HashSet<>();
    @ManyToOne
    @EqualsAndHashCode.Exclude

    @JsonBackReference(value = "curso_professores")
    private Curso curso;
    @ManyToOne
    //@EqualsAndHashCode.Exclude
    //@ToString.Exclude
    //@JsonManagedReference(value = "professor_qualificacao")
    private Qualificacao grau;

    public Professor(String nome, int num) {
        this.setNome(nome);
        this.setNum(num);
    }

    public Professor(Long id, String nome, int num, Qualificacao grau, Curso curso, Set<Cadeira> cadeiras, Set<Atendimento> atendimentos, Set<Idioma> idiomas, Set<Horario> horarios) {
        this.setId(id);
        this.setNome(nome);
        this.setNum(num);
        this.associateQualificacao(grau);
        this.associateCurso(curso);

        for (Atendimento atendimento : atendimentos)
            this.addAtendimento(atendimento);
        for (Cadeira cadeira : cadeiras)
            this.addCadeira(cadeira);
        for (Idioma idioma : idiomas)
            this.addIdioma(idioma);
        for (Horario horario : horarios)
            this.addHorario(horario);
    }

    public void addAtendimento(Atendimento atendimento) {
        this.atendimentos.add(atendimento);
    }

    public void addIdioma(Idioma idioma) {
        this.idiomas.add(idioma);
    }

    public void associateQualificacao(Qualificacao grau) {
        this.setGrau(grau);
    }

    public void associateCurso(Curso curso) {
        this.setCurso(curso);
    }

    public void addHorario(Horario horario) {
        horario.setProfessor(this);
        this.horarios.add(horario);
    }

    public void addCadeira(Cadeira cadeira) {
        this.cadeiras.add(cadeira);
    }

}
