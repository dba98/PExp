package com.plataforma.explicacoes.services.filters.professor;

import com.plataforma.explicacoes.models.Idioma;
import com.plataforma.explicacoes.models.Qualificacao;
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
    private String idioma;
    private Integer grau;
    public FilterProfessorObject(Integer curso, DayOfWeek dia, LocalTime inicio, LocalTime fim, String idioma, Integer grau) {
        this.curso = curso;
        this.dia = dia;
        this.inicio = inicio;
        this.fim = fim;
        this.idioma = idioma;
        this.grau = grau;

    }

    public FilterProfessorObject(Map<String, String> parameters) {

        String curso = parameters.get("curso");
        String dia = parameters.get("dia");
        String inicio = parameters.get("inicio");
        String fim = parameters.get("fim");
        String idioma = parameters.get("idioma");
        String grau = parameters.get("grau");

        if (curso == null && dia == null && inicio == null && fim == null && idioma == null && grau == null)
            logger.warn("Nenhum parametro inserido");
        else {
            if (curso != null && !curso.equals("null"))
                this.curso = Integer.parseInt(curso);
            if (dia != null && !dia.equals("null"))
                this.dia = DayOfWeek.of(Integer.parseInt(dia));
            if (inicio != null && !inicio.equals("null"))
                this.inicio = LocalTime.parse(inicio);
            if (fim != null && !fim.equals("null"))
                this.fim = LocalTime.parse(fim);
            if(idioma != null && !idioma.equals("null"))
                this.idioma = idioma;
            if (grau != null && !grau.equals("null"))
                this.grau = Integer.parseInt(grau);
        }

    }
}
