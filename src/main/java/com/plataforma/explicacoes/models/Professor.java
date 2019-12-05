package com.plataforma.explicacoes.models;

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
//    @JsonManagedReference(value = "professor_cadeira")
    @JsonIgnore
    private Set<Cadeira> cadeiras = new HashSet<>();

    @OneToMany(cascade = CascadeType.PERSIST)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonManagedReference(value = "professores_horarios")
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
        this.horarios.add(horario);
    }

}
