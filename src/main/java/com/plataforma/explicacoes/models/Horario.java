package com.plataforma.explicacoes.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.logging.Logger;


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
    //@JsonBackReference
    @JsonIgnore
    private Professor professor;


    public Horario(DayOfWeek dia,String hInicio,String hFim){
        System.out.println("Estou aqui");
        this.setDia(dia);
        this.setHInicio(LocalTime.parse(hInicio));
        this.setHFim(LocalTime.parse(hFim));

    }
    public Horario (Professor professor, DayOfWeek dia,String hInicio,String hFim){
        System.out.println("Estou aqui 2");
        this.setDia(dia);
        this.setProfessor(professor);
        this.setHInicio(LocalTime.parse(hInicio));
        this.setHFim(LocalTime.parse(hFim));
    }
}
