package com.plataforma.explicacoes.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Data
@Entity
@NoArgsConstructor
public class Horario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dia;
    private LocalDateTime hInicio;
    private LocalDateTime hFim;


    @ManyToOne
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonBackReference
    private Professor professor;

    public Horario (LocalDate dia, Professor professor,LocalDateTime hInicio,LocalDateTime hFim){
        this.setDia(dia);
        this.setProfessor(professor);
        this.setHInicio(hInicio);
        this.setHFim(hFim);
    }
}
