package com.plataforma.explicacoes.services.filters.professor;

import com.plataforma.explicacoes.models.Horario;
import com.plataforma.explicacoes.models.Professor;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

public class ProfessorFilterByHoraFim implements FilterI<Professor>{
    private LocalTime fim;

    public ProfessorFilterByHoraFim(LocalTime fim){
        this.fim = fim;
    }
    @Override
    public Set<Professor> filter(Set<Professor> entitites){
        if(this.fim == null)
            return entitites;
        Set<Professor> professors = new HashSet<>();
        for (Professor professor : entitites){
            for (Horario horario : professor.getHorarios()){
                if (horario.getFim().isAfter(this.fim))
                    professors.add(professor);
            }
        }
        return professors;
    }
}
