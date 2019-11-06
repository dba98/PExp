package com.plataforma.explicacoes.models;

import lombok.Data;
import org.hibernate.mapping.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Professor {

    private String name;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int num;
    public Professor(String name,int num){
        this.name = name;
        this.num = num;
    }


}
