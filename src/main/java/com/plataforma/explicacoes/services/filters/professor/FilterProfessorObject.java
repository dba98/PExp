package com.plataforma.explicacoes.services.filters.professor;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Map;

@Data
public class FilterProfessorObject {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private Integer curso;
    private DayOfWeek dia;
    private LocalTime inicio;
    private LocalTime fim;

    public FilterProfessorObject(Integer curso , DayOfWeek dia,LocalTime inicio,LocalTime fim){
        this.curso = curso;
        this.dia = dia;
        this.inicio = inicio;
        this.fim = fim;
    }

    public FilterProfessorObject(Map<String, String> parameters) {

        String curso = parameters.get("curso");
        String dia = parameters.get("dia");
        String inicio = parameters.get("inicio");
        String fim = parameters.get("fim");

        Integer auxcurso = null;
        DayOfWeek auxdia = null;
        LocalTime auxinicio = null;
        LocalTime auxfim = null;

        try {
            auxcurso = Integer.parseInt(curso);
            auxdia = DayOfWeek.of(Integer.parseInt(dia));
            System.out.println(curso+" "+dia+" "+inicio+" "+fim);
            auxinicio = LocalTime.parse(inicio);
            auxfim = LocalTime.parse(fim);
        } catch (Exception e) {
            this.logger.error(e.getMessage());
        }
        this.curso = auxcurso;
        this.dia = auxdia;
        this.inicio = auxinicio;
        this.fim = auxfim;


    }
}
