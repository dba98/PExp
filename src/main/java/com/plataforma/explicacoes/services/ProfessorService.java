package com.plataforma.explicacoes.services;

import com.plataforma.explicacoes.models.Horario;
import com.plataforma.explicacoes.models.Professor;
import com.plataforma.explicacoes.repositories.ProfessorRepo;
import org.apache.tomcat.util.json.JSONParser;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.*;
import java.time.DayOfWeek;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepo professorRepo;

    public Set<Professor> findAll() {
        Set<Professor> professores = new HashSet<>();
        for (Professor p1 : this.professorRepo.findAll()) {
            professores.add(p1);
        }
        return professores;
    }

    public Optional<Professor> findById(Long id) {
        return this.professorRepo.findById(id);
    }

    public Optional<Professor> createProfessor(Professor professor) {
        Optional<Professor> optionalProfessor = this.professorRepo.findByName(professor.getName());
        if (optionalProfessor.isEmpty()) {
            return Optional.of(this.professorRepo.save(professor));
        }
        return Optional.empty();
    }

    public Optional<Professor> createHorario(String jsonString) {
        Optional<Professor> optionalProfessor = jsonParsing(jsonString);

        if (optionalProfessor.isPresent()) {
            return Optional.of(professorRepo.save(optionalProfessor.get()));
        }
        return Optional.empty();
    }


    public Optional<Professor> jsonParsing(Map<String,string> jsonString) {


        Optional<Professor> auxProfessor = this.findById(Long.parseLong(, 10));

        for (Horario horario : auxProfessor.get().getHorarios()) {
            if (horario.getDia().getValue() == dia.getValue())
                if (!LocalTime.parse(hInicio).isAfter(horario.getHFim()) || !LocalTime.parse(hFim).isBefore(horario.getHInicio())) {
                    return Optional.empty();
                }

        }
        /*Horario createdHorario = new Horario(auxProfessor.get(), dia, LocalTime.parse(hInicio), LocalTime.parse(hFim));
        auxProfessor.get().addHorario(createdHorario);

        this.professorRepo.save(auxProfessor.get());

        System.out.println(createdHorario);
        System.out.println(createdHorario.getProfessor());

        System.out.println(auxProfessor.get().getHorarios());*/



        return auxProfessor;

    }

}
