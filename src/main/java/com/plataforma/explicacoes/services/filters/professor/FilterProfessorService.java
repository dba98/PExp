package com.plataforma.explicacoes.services.filters.professor;

import com.plataforma.explicacoes.models.Professor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.logging.Filter;

@Service
public class FilterProfessorService {
    public Set<Professor> filter(Set<Professor> professors, FilterProfessorObject filterProfessorObject) {

        FilterI<Professor> professorFilterByCurso = new ProfessorFilterByCurso(filterProfessorObject.getCurso());
        FilterI<Professor> professorFilterByDia = new ProfessorFilterByDia(filterProfessorObject.getDia());
        FilterI<Professor> professorFilterByHoraInicio = new ProfessorFilterByHoraInicio(filterProfessorObject.getInicio());
        FilterI<Professor> professorFilterByHoraFim = new ProfessorFilterByHoraFim(filterProfessorObject.getFim());
        FilterI<Professor> professorFilterByIdiomas = new ProfessorFilterByIdiomas(filterProfessorObject.getIdioma());
        FilterI<Professor> professorFilterByQualificacao = new ProfessorFilterByQualificacao(filterProfessorObject.getGrau());
        FilterI<Professor> professorFilterByCursoAndDia = new AndFilter<>(professorFilterByCurso, professorFilterByDia);
        FilterI<Professor> professorFilterByorainicioAndHoraFim = new AndFilter<>(professorFilterByHoraInicio, professorFilterByHoraFim);
        FilterI<Professor> professorFilterByIdiomaAndQualificacao = new AndFilter<>(professorFilterByIdiomas,professorFilterByQualificacao);
        FilterI<Professor> professorFilterByIdiomaAndQualificacaoAndCursoAndDia = new AndFilter<>(professorFilterByIdiomaAndQualificacao,professorFilterByCursoAndDia);

        return new AndFilter<>(professorFilterByIdiomaAndQualificacaoAndCursoAndDia, professorFilterByorainicioAndHoraFim).filter(professors);
    }
}
