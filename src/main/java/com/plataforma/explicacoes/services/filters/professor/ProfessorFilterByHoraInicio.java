package com.plataforma.explicacoes.services.filters.professor;

import com.plataforma.explicacoes.models.Horario;
import com.plataforma.explicacoes.models.Professor;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

public class ProfessorFilterByHoraInicio implements FilterI<Professor>{

    private LocalTime inicio;

    public ProfessorFilterByHoraInicio(LocalTime inicio){
        this.inicio = inicio;
    }

    public Set<Professor> filter(Set<Professor> entitites){
        if(this.inicio == null)
            return entitites;
        Set<Professor> professors = new HashSet<>();
        for (Professor professor : entitites){
            for (Horario horario : professor.getHorarios()){
                if (horario.getInicio().isBefore(this.inicio))
                    professors.add(professor);
            }
        }
        return professors;
    }

}
