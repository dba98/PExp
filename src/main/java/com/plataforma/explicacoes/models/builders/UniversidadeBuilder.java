package com.plataforma.explicacoes.models.builders;

import com.plataforma.explicacoes.models.Faculdade;
import com.plataforma.explicacoes.models.Universidade;

import java.util.HashSet;
import java.util.Set;

public class UniversidadeBuilder {

    private String name;
    private Set<Faculdade> faculdades = new HashSet<>();

    public UniversidadeBuilder setName(String name){
        this.name= name;
        return this;
    }

    public UniversidadeBuilder setFaculdade(Set<Faculdade> faculdades){
        this.faculdades= faculdades;
        return this;
    }

    public UniversidadeBuilder addFaculdade(Faculdade faculdade){
        this.faculdades.add(faculdade);
        return this;
    }

    public Universidade build(){ return new Universidade(name, faculdades); }
}
