package com.plataforma.explicacoes.services.filters.professor;

import com.plataforma.explicacoes.models.Horario;
import com.plataforma.explicacoes.models.Professor;
import com.plataforma.explicacoes.models.Qualificacao;

import java.time.DayOfWeek;
import java.util.HashSet;
import java.util.Set;

public class ProfessorFilterByQualificacao implements FilterI<Professor>{
    private Integer grau;

    public ProfessorFilterByQualificacao (Integer grau){
        this.grau = grau;
    }
    @Override
    public Set<Professor> filter (Set<Professor> entities){
        if(this.grau == null)
            return entities;
        Set<Professor> professors = new HashSet<>();
        for(Professor professor : entities){
                if(professor.getGrau().getVal().equals(grau))
                    professors.add(professor);
            }
        return professors;
    }
}
