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

    public FilterProfessorObject(Integer curso, DayOfWeek dia, LocalTime inicio, LocalTime fim) {
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

        if (curso == null && dia == null && inicio == null && fim == null)
            logger.warn("Nenhum parametro inserido");
        else {
            if (curso != null)
                this.curso = Integer.parseInt(curso);
            if (dia != null)
                this.dia = DayOfWeek.of(Integer.parseInt(dia));
            if (inicio != null)
                this.inicio = LocalTime.parse(inicio);
            if (fim != null)
                this.fim = LocalTime.parse(fim);
        }

    }
}
