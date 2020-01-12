package com.plataforma.explicacoes.services.filters.professor;

import com.plataforma.explicacoes.models.Horario;
import com.plataforma.explicacoes.models.Idioma;
import com.plataforma.explicacoes.models.Professor;

import javax.servlet.Filter;
import java.util.HashSet;
import java.util.Set;

public class ProfessorFilterByIdiomas implements FilterI<Professor> {
    private String idioma;

    public ProfessorFilterByIdiomas(String idioma){
        this.idioma = idioma;
    }

    @Override
    public Set<Professor> filter(Set<Professor> entities){
        if (this.idioma == null){
            return entities;
        }
        Set<Professor> professors = new HashSet<>();
        for (Professor professor : entities){
            for (Idioma idioma : professor.getIdiomas()){
                if(idioma.getName().equals(this.idioma)){
                    professors.add(professor);
                }
            }
        }
        return professors;
    }
}
