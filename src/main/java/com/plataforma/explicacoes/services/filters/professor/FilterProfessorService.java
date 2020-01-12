package com.plataforma.explicacoes.services.filters.professor;

import com.plataforma.explicacoes.models.Professor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class FilterProfessorService {
    public Set<Professor> filter(Set<Professor> professors, FilterProfessorObject filterProfessorObject) {


        FilterI<Professor> professorFilterByCurso = new ProfessorFilterByCurso(filterProfessorObject.getCurso());
        FilterI<Professor> professorFilterByDia = new ProfessorFilterByDia(filterProfessorObject.getDia());
        FilterI<Professor> professorFilterByHoraInicio = new ProfessorFilterByHoraInicio(filterProfessorObject.getInicio());
        FilterI<Professor> professorFilterByHoraFim = new ProfessorFilterByHoraFim(filterProfessorObject.getFim());
        FilterI<Professor> professorFilterByCursoAndDia = new AndFilter<>(professorFilterByCurso, professorFilterByDia);
        FilterI<Professor> professorFilterByorainicioAndHoraFim = new AndFilter<>(professorFilterByHoraInicio, professorFilterByHoraFim);

        return new AndFilter<>(professorFilterByCursoAndDia, professorFilterByorainicioAndHoraFim).filter(professors);
    }
}
