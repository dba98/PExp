package com.plataforma.explicacoes.services;

import com.plataforma.explicacoes.models.Horario;
import com.plataforma.explicacoes.models.Professor;
import com.plataforma.explicacoes.repositories.ProfessorRepo;
import org.apache.tomcat.util.json.JSONParser;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
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


    public Optional<Professor> jsonParsing(String jsonString) {
        JSONParser parser = new JSONParser(jsonString);
        String hInicio = "", hFim = "", professorId = "", auxdia = "";
        DayOfWeek dia = null;
        ArrayList<String> individualValues = new ArrayList<>();
        while (!parser.getNextToken().toString().isEmpty()) {
            individualValues.add(parser.getNextToken().toString().replaceAll("\"", ""));
        }
        for (int i = 0; i < individualValues.size(); i++) {
            switch (i) {
                case 1:
                    professorId = individualValues.get(i);
                    break;
                case 3:
                    auxdia = individualValues.get(i);
                    dia = DayOfWeek.of(Integer.parseInt(auxdia));
                    break;
                case 5:
                    hInicio = individualValues.get(i);
                    break;
                case 7:
                    hFim = individualValues.get(i);
                    break;
            }
        }

        Optional<Professor> auxProfessor = this.findById(Long.parseLong(professorId, 10));

        for (Horario horario : auxProfessor.get().getHorarios()) {
            if (horario.getDia().getValue() == dia.getValue())
                if (!LocalTime.parse(hInicio).isAfter(horario.getHFim()) || !LocalTime.parse(hFim).isBefore(horario.getHInicio())) {
                    return Optional.empty();
                }

        }
        Horario createdHorario = new Horario(dia, auxProfessor.get(), LocalTime.parse(hInicio), LocalTime.parse(hFim));
        auxProfessor.get().addHorario(createdHorario);
        return auxProfessor;

    }

}
