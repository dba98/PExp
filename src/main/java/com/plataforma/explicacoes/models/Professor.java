package com.plataforma.explicacoes.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int num;

    @OneToMany(mappedBy = "professor")
    @JsonManagedReference(value = "professores_atendimentos")
    private Set<Atendimento> atendimentos = new HashSet<>();

    @ManyToMany
    @EqualsAndHashCode.Exclude
    //@ToString.Exclude
//    @JsonManagedReference(value = "professor_idioma")
    private Set<Idioma> idiomas = new HashSet<>();

    @ManyToMany
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonBackReference(value = "professor_cadeira")
   // @JsonIgnore
    private Set<Cadeira> cadeiras = new HashSet<>();

    @OneToMany(cascade = CascadeType.PERSIST)
    @EqualsAndHashCode.Exclude
    //@ToString.Exclude
    @JsonBackReference(value = "professores_horarios")
   // @JsonIgnore
    private Set<Horario> horarios = new HashSet<>();

    @ManyToOne
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    // @JsonManagedReference(value = "professor_qualificacao")
    private Qualificacao grau;

    public Professor(String name, int num) {
        this.setName(name);
        this.setNum(num);
    }

    public Professor(Long id, String name, int num, Qualificacao grau, Set<Atendimento> atendimentos, Set<Idioma> idiomas, Set<Cadeira> cadeiras, Set<Horario> horarios) {
        this.setId(id);
        this.setName(name);
        this.setNum(num);
        this.associateQualificacao(grau);
        for(Atendimento atendimento: atendimentos)
            this.addAtendimento(atendimento);
        for(Cadeira cadeira: cadeiras)
            this.addCadeira(cadeira);
        for(Idioma idioma: idiomas)
            this.addIdioma(idioma);
        for(Horario horario:horarios)
            this.addHorario(horario);
    }

    public void addAtendimento(Atendimento atendimento){
        this.atendimentos.add(atendimento);
    }

    public void addCadeira(Cadeira cadeira) {
        this.cadeiras.add(cadeira);
    }

    public void addIdioma(Idioma idioma) {
        this.idiomas.add(idioma);
    }

    public void associateQualificacao(Qualificacao grau) {
        this.setGrau(grau);
    }

    public void addHorario(Horario horario) {
        horario.setProfessor(this);
        this.horarios.add(horario);

    }

}
