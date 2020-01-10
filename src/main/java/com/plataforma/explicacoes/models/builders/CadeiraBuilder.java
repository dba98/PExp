package com.plataforma.explicacoes.models.builders;

import com.plataforma.explicacoes.models.Cadeira;
import com.plataforma.explicacoes.models.Curso;
import com.plataforma.explicacoes.models.Professor;

import java.util.HashSet;
import java.util.Set;

public class CadeiraBuilder {

    private String name;
    private Integer codigo;
    private Curso curso;
    private Set<Professor> professores=new HashSet<>();

    public CadeiraBuilder setName(String name) {
        this.name= name;
        return this;
    }

    public CadeiraBuilder setCodigo(Integer codigo){
        this.codigo= codigo;
        return this;
    }

    public CadeiraBuilder setCurso(Curso curso) {
        this.curso= curso;
        return this;
    }

    public CadeiraBuilder setProfessores(Set<Professor> professores){
        this.professores= professores;
        return this;
    }

    public CadeiraBuilder addProfessores(Professor professor){
        this.professores.add(professor);
        return this;
    }

    public Cadeira build(){ return new Cadeira(name, codigo, professores, curso);}
}
