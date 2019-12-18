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

    @ManyToOne(cascade = CascadeType.PERSIST)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonBackReference
    //@JsonIgnore
    private Professor professor;


    public Horario(DayOfWeek dia,LocalTime hInicio,LocalTime hFim){
        System.out.println("Estou aqui");
        this.setDia(dia);
        this.setHInicio(hInicio);
        this.setHFim(hFim);

    }
    public Horario (Professor professor, DayOfWeek dia,LocalTime hInicio,LocalTime hFim){
        System.out.println("Estou aqui 2");
        this.setDia(dia);
        this.setProfessor(professor);
        this.setHInicio(hInicio);
        this.setHFim(hFim);
    }
}
