package com.plataforma.explicacoes.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;
import javax.persistence.CascadeType;

import java.time.DayOfWeek;
import java.time.LocalTime;


@Data
@Entity
@NoArgsConstructor
public class Horario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private DayOfWeek dia;
    private LocalTime inicio;
    private LocalTime fim;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonBackReference("professores_horarios")
    private Professor professor;


    public Horario(DayOfWeek dia,LocalTime inicio,LocalTime fim){
        this.setDia(dia);
        this.setInicio(inicio);
        this.setFim(fim);

    }
    public Horario (Professor professor, DayOfWeek dia,LocalTime inicio,LocalTime fim){
        this.setDia(dia);
        this.setProfessor(professor);
        this.setInicio(inicio);
        this.setFim(fim);
    }
}
