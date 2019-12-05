package com.plataforma.explicacoes.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
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
    private LocalTime hInicio;
    private LocalTime hFim;


    @ManyToOne
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonBackReference
    private Professor professor;

    public Horario (DayOfWeek dia, Professor professor,LocalTime hInicio,LocalTime hFim){
        this.setDia(dia);
        this.setProfessor(professor);
        this.setHInicio(hInicio);
        this.setHFim(hFim);
    }
}
