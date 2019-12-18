package com.plataforma.explicacoes.services;

import com.plataforma.explicacoes.models.Horario;
import com.plataforma.explicacoes.models.Professor;
import com.plataforma.explicacoes.repositories.HorarioRepo;
import com.plataforma.explicacoes.repositories.ProfessorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Map;
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

    public Optional<Professor> createProfessor(Professor professor) {
        Optional<Professor> optionalProfessor = this.professorRepo.findByName(professor.getName());
        if (optionalProfessor.isEmpty()) {
            return Optional.of(this.professorRepo.save(professor));
        }
        return Optional.empty();
    }



    public Optional<Professor> createHorario(Map<String,String> jsonHorario) {


        Optional<Professor> optionalProfessor = this.findById(Long.parseLong(jsonHorario.get("idProfessor")));


        if (optionalProfessor.isEmpty()) {
            return Optional.empty();
        }


        for (Horario horario : optionalProfessor.get().getHorarios()) {
            if (horario.getDia().getValue() == Integer.parseInt(jsonHorario.get("dia")))
                if (!LocalTime.parse(jsonHorario.get("hInicio")).isAfter(horario.getHFim()) || !LocalTime.parse(jsonHorario.get("hFim")).isBefore(horario.getHInicio())) {
                    return Optional.empty();
                }
        }
        Horario h= new Horario( DayOfWeek.of(Integer.parseInt(jsonHorario.get("dia"))), LocalTime.parse(jsonHorario.get("hInicio")), LocalTime.parse(jsonHorario.get("hFim")));
        optionalProfessor.get().addHorario(h);

        System.out.println(h);
        System.out.println(optionalProfessor.get().getHorarios());
        return Optional.of(professorRepo.save(optionalProfessor.get()));
    }
}
