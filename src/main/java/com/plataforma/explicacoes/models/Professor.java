package com.plataforma.explicacoes.models;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int num;

    @OneToMany(mappedBy = "atendimento")
    private Set<Atendimento> atendimentos = new HashSet<>();

    public Professor(String name, int num) {
        this.setName(name);
        this.setNum(num);
    }
}
