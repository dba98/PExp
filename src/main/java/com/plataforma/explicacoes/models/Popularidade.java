package com.plataforma.explicacoes.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Popularidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int count;

    @OneToOne
    private Professor professor;

    public Popularidade(int count) {
        this.setCount(count);
    }
}
