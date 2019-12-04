package com.plataforma.explicacoes.services;

import com.plataforma.explicacoes.models.Horario;
import com.plataforma.explicacoes.models.Professor;
import com.plataforma.explicacoes.repositories.ProfessorRepo;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

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

    public Optional<Professor> createProfessor(Professor professor){
        Optional<Professor> optionalProfessor = this.professorRepo.findByName(professor.getName());
        if (optionalProfessor.isEmpty()){
            return Optional.of(this.professorRepo.save(professor));
        }
        return Optional.empty();
    }
    public Optional<Horario> createHorario(String jsonString){
        JSONParser parser = new JSONParser(jsonString);
        String hInicio = "", hFim = "", professorId = "";
        ArrayList<String> individualValues = new ArrayList<>();
        Long id = 1L;
        while (!parser.getNextToken().toString().isEmpty()) {
            individualValues.add(parser.getNextToken().toString().replaceAll("\"", ""));
        }
        for (int i = 0; i < individualValues.size(); i++) {
            switch (i) {
                case 1:
                    professorId = individualValues.get(i);
                    break;
                case 3:
                    hInicio = individualValues.get(i);
                    break;
                case 5:
                    hFim = individualValues.get(i);
                    break;
            }
        }

        Optional<Professor> auxProfessor = this.findById(Long.parseLong(professorId, 10));


        Horario horario = new Horario(LocalDate.parse(dia), auxProfessor.get(), LocalTime.parse(hInicio), LocalTime.parse(hFim));

        System.out.println(LocalDate.parse(dia) + " " + auxProfessor.get() + " " + LocalTime.parse(hInicio) + " " + LocalTime.parse(hFim));
    }

}
