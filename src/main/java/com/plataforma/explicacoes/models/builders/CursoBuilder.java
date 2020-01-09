package com.plataforma.explicacoes.models.builders;

import com.plataforma.explicacoes.models.Aluno;
import com.plataforma.explicacoes.models.Cadeira;
import com.plataforma.explicacoes.models.Curso;
import com.plataforma.explicacoes.models.Faculdade;
import java.util.HashSet;
import java.util.Set;

public class CursoBuilder {

    private Long id;
    private String nome;
    private Integer codigo;
    private Faculdade faculdade;
    private Set<Cadeira> cadeiras = new HashSet<>();
    private Set<Aluno> alunos = new HashSet<>();

    public CursoBuilder setId(Long id){
        this.id=id;
        return this;
    }

    public CursoBuilder setNome(String nome) {
        this.nome= nome;
        return this;
    }

    public CursoBuilder setCodigo(Integer codigo){
        this.codigo= codigo;
        return this;
    }

    public CursoBuilder setFaculdade(Faculdade faculdade){
        this.faculdade=faculdade;
        return this;
    }

    public CursoBuilder setCadeiras(Set<Cadeira> cadeiras){
        this.cadeiras= cadeiras;
        return this;
    }

    public CursoBuilder addCadeiras(Cadeira cadeira){
        this.cadeiras.add(cadeira);
        return this;
    }

    public CursoBuilder setAlunos(Set<Aluno> alunos){
        this.alunos= alunos;
        return this;
    }

    public CursoBuilder addAlunos(Aluno aluno){
        this.alunos.add(aluno);
        return this;
    }

    public Curso build(){ return new Curso(id,nome, codigo, faculdade, cadeiras, alunos);}

}
