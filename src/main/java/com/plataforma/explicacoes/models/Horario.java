package com.plataforma.explicacoes.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity

public class Horario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date dia;

    @ManyToOne
    @JsonBackReference
    private Professor professor;

    public Horario (Date dia, Professor professor){
        this.setDia(dia);
        this.setProfessor(professor);
    }
}
