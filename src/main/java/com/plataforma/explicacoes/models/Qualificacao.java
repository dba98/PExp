package com.plataforma.explicacoes.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
public class Qualificacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    private Integer val;

    @OneToMany
   // @JsonManagedReference(value = "professor_qualificacao")
    @JsonIgnore
    private Set<Professor> professores = new HashSet<>();

    public Qualificacao(String name, Integer val) {
        this.setName(name);
        this.setVal(val);
    }

    public void addProfessor(Professor professor){
        this.professores.add(professor);
    }
}
