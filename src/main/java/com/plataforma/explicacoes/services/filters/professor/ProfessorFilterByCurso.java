package com.plataforma.explicacoes.services.filters.professor;

import com.plataforma.explicacoes.models.Cadeira;
import com.plataforma.explicacoes.models.Professor;
import java.util.HashSet;
import java.util.Set;

public class ProfessorFilterByCurso implements FilterI<Professor>{

    private Integer curso;

    public ProfessorFilterByCurso(Integer curso){
        this.curso = curso;
    }

    @Override
    public Set<Professor> filter(Set<Professor> entities){
        System.out.println(entities);
        if(curso == null){
            return entities;
        }
        Set<Professor> professors = new HashSet<>();
        for (Professor professor : entities){
            for (Cadeira cadeira : professor.getCadeiras()){
                System.out.println("Cadeira curso: "+cadeira.getCurso().getCodigo()+" Curso:"+curso);
                if (cadeira.getCurso().getCodigo().equals(curso))
                    professors.add(professor);
            }
        }
        return professors;
    }


}
