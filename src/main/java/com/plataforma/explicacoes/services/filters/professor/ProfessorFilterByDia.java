package com.plataforma.explicacoes.services.filters.professor;

import com.plataforma.explicacoes.models.Horario;
import com.plataforma.explicacoes.models.Professor;

import java.time.DayOfWeek;
import java.util.HashSet;
import java.util.Set;

public class ProfessorFilterByDia implements FilterI<Professor>{
    private DayOfWeek dia;

    public ProfessorFilterByDia (DayOfWeek dia){
        this.dia = dia;
    }
    @Override
    public Set<Professor> filter (Set<Professor> entities){
        if(this.dia == null)
            return entities;

        Set<Professor> professors = new HashSet<>();
        for(Professor professor : entities){
            for(Horario horario : professor.getHorarios()){
                System.out.println("Horario dia: "+horario.getDia()+" Dia:"+dia);
                if(horario.getDia().equals(dia))
                    professors.add(professor);
            }
        }
        return professors;
    }
}
