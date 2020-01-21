package com.plataforma.explicacoes.services.filters.professor;

import com.plataforma.explicacoes.models.*;
import com.plataforma.explicacoes.models.builders.ProfessorBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Filter;

import static org.junit.jupiter.api.Assertions.*;

class FilterProfessorServiceTest {

    @Test
    void filter() {

        Set<Professor> professors = new HashSet<>();
        Curso curso1 = new Curso("Engenharia Informatica",10);
        Cadeira cadeira1 =new Cadeira("Matematica",15);
        Idioma idioma1 = new Idioma("Portugues");
        Qualificacao grau1 = new Qualificacao("Mestre",2);

        Curso curso2 = new Curso("Engenharia Mecanica",12);
        Cadeira cadeira2 =new Cadeira("Engenharia de Software",124);
        Idioma idioma2 = new Idioma("Ingles");
        Qualificacao grau2 = new Qualificacao("Doutor",3);
        curso1.addCadeira(cadeira1);
        cadeira1.associateCurso(curso1);
        curso2.addCadeira(cadeira2);
        cadeira2.associateCurso(curso2);
        Professor professor1 = new ProfessorBuilder().setId(1L).setCurso(curso1).setName("Alessandro Moreira").setNum(35234).setGrau(grau1).addCadeira(cadeira1).addIdioma(idioma1).addHorario(new Horario(DayOfWeek.MONDAY, LocalTime.of(10, 0), LocalTime.of(12, 0))).build();
        Professor professor2 = new ProfessorBuilder().setId(2L).setCurso(curso2).setName("Feliz Gouveia").setNum(35244).setGrau(grau2).addCadeira(cadeira2).addIdioma(idioma2).addHorario(new Horario(DayOfWeek.MONDAY, LocalTime.of(12, 0), LocalTime.of(13, 0))).build();

        idioma1.addProfessor(professor1);
        grau1.addProfessor(professor1);
        curso1.addProfessor(professor1);
        cadeira1.addProfessor(professor1);

        idioma2.addProfessor(professor2);
        grau2.addProfessor(professor2);
        curso2.addProfessor(professor2);
        cadeira2.addProfessor(professor2);

        professors.add(professor1);
        professors.add(professor2);

        FilterProfessorObject filterProfessorObject = new FilterProfessorObject(curso1.getCodigo(),DayOfWeek.MONDAY,LocalTime.of(10,0),LocalTime.of(12,0),idioma1.getName(),grau1.getVal());
        FilterProfessorService filterProfessorService = new FilterProfessorService();
        assertEquals(1,filterProfessorService.filter(professors, filterProfessorObject).size());
        Map<String,String> map= new HashMap<>();

        map.put("curso",null);
        map.put("dia",null);
        map.put("inicio",null);
        map.put("fim",null);
        map.put("idioma",null);
        map.put("grau",null);
        filterProfessorObject = new FilterProfessorObject(map);
        filterProfessorService = new FilterProfessorService();
        assertEquals(2,filterProfessorService.filter(professors,filterProfessorObject).size());

        map = new HashMap<>();
        map.put("curso","10");
        map.put("dia","1");
        map.put("inicio","10:00");
        map.put("fim","12:00");
        map.put("idioma","Portugues");
        map.put("grau","2");

        filterProfessorObject = new FilterProfessorObject(map);
        filterProfessorService = new FilterProfessorService();
        assertEquals(1,filterProfessorService.filter(professors,filterProfessorObject).size());

    }
}