package com.plataforma.explicacoes.models.builders;

import com.plataforma.explicacoes.models.*;

import java.util.HashSet;
import java.util.Set;

public class ProfessorBuilder {

    private Long id;
    private String name;
    private int num;
    private Set<Atendimento> atendimentos = new HashSet<>();
    private Set<Idioma> idiomas = new HashSet<>();
    private Set<Cadeira> cadeiras = new HashSet<>();
    private Set<Horario> horarios = new HashSet<>();
    private Qualificacao grau;

    public ProfessorBuilder setId(Long id){
        this.id=id;
        return this;
    }

    public ProfessorBuilder setName(String name){
        this.name=name;
        return this;
    }

    public ProfessorBuilder setNum(int num){
        this.num=num;
        return this;
    }

    public ProfessorBuilder setGrau(Qualificacao grau){
        this.grau=grau;
        return this;
    }

    public ProfessorBuilder setAtendimentos(Set<Atendimento> atendimentos){
        this.atendimentos= atendimentos;
        return this;
    }

    public ProfessorBuilder addAtendimento(Atendimento atendimento){
        this.atendimentos.add(atendimento);
        return this;
    }

    public ProfessorBuilder setIdiomas(Set<Idioma> idiomas){
        this.idiomas= idiomas;
        return this;
    }

    public ProfessorBuilder addIdioma(Idioma idioma){
        this.idiomas.add(idioma);
        return this;
    }

    public ProfessorBuilder setCadeiras(Set<Cadeira> cadeiras){
        this.cadeiras= cadeiras;
        return this;
    }

    public ProfessorBuilder addCadeira(Cadeira cadeira){
        this.cadeiras.add(cadeira);
        return this;
    }

    public ProfessorBuilder setHorarios(Set<Horario> horarios){
        this.horarios= horarios;
        return this;
    }

    public ProfessorBuilder addHorario(Horario horario){
        this.horarios.add(horario);
        return this;
    }

    public Professor build(){
        return new Professor(id,name,num,grau,atendimentos,idiomas,cadeiras,horarios);
    }
}
