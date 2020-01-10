package com.plataforma.explicacoes.models.builders;

import com.plataforma.explicacoes.models.Aluno;
import com.plataforma.explicacoes.models.Atendimento;
import com.plataforma.explicacoes.models.Curso;

import java.util.HashSet;
import java.util.Set;

public class AlunoBuilder {

    private Long id;
    private int num;
    private String name;
    private Curso curso;
    private Set<Atendimento> atendimentos = new HashSet<>();

    public AlunoBuilder setId(Long id){
        this.id=id;
        return this;
    }

    public AlunoBuilder setNum(int num){
        this.num=num;
        return this;
    }

    public AlunoBuilder setName(String name){
        this.name=name;
        return this;
    }

    public AlunoBuilder setCurso(Curso curso){
        this.curso=curso;
        return this;
    }

    public AlunoBuilder setAtendimentos(Set<Atendimento> atendimentos){
        this.atendimentos= atendimentos;
        return this;
    }

    public AlunoBuilder addAtendimento(Atendimento atendimento){
        this.atendimentos.add(atendimento);
        return this;
    }

    public Aluno build(){
        return new Aluno(num, name, curso, atendimentos);
    }

}
