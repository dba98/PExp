package com.plataforma.explicacoes.services.filters.professor;

import com.plataforma.explicacoes.models.*;
import com.plataforma.explicacoes.models.builders.ProfessorBuilder;
import com.sun.xml.bind.v2.runtime.BinderImpl;
import org.apache.tomcat.jni.Local;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class AndFilterTest {

    @Test
    void filter() {

        LocalTime inicio = LocalTime.of(10, 0);
        Set<Professor> professors = new HashSet<>();


        Professor professor1 = new ProfessorBuilder().setId(1L).setName("Alessandro Moreira").setNum(35234).setGrau(new Qualificacao("Mestre", 2)).addCadeira(new Cadeira("Matematica", 123)).addIdioma(new Idioma("Portugues")).addHorario(new Horario(DayOfWeek.MONDAY, LocalTime.of(10, 0), LocalTime.of(12, 0))).build();
        Professor professor2 = new ProfessorBuilder().setId(2L).setName("Feliz Goubeia").setNum(35244).setGrau(new Qualificacao("Doutor", 3)).addCadeira(new Cadeira("Engenharia de Software", 124)).addIdioma(new Idioma("Ingles")).addHorario(new Horario(DayOfWeek.MONDAY, LocalTime.of(12, 0), LocalTime.of(13, 0))).build();

        professors.add(professor1);
        professors.add(professor2);

        ProfessorFilterByDia professorFilterByDia = new ProfessorFilterByDia(DayOfWeek.MONDAY);
        ProfessorFilterByHoraInicio professorFilterByHoraInicio = new ProfessorFilterByHoraInicio(inicio);
        AndFilter andFilter = new AndFilter(professorFilterByDia, professorFilterByHoraInicio);
        assertEquals(1, andFilter.filter(professors).size());
        ;
        professorFilterByDia = new ProfessorFilterByDia(null);
        professorFilterByHoraInicio = new ProfessorFilterByHoraInicio(null);
        andFilter = new AndFilter(professorFilterByDia, professorFilterByHoraInicio);
        assertEquals(2, andFilter.filter(professors).size());




    }


}
