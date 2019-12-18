package com.plataforma.explicacoes.services;

import com.plataforma.explicacoes.exceptions.ProfessorDoesNotExistException;
import com.plataforma.explicacoes.models.Horario;
import com.plataforma.explicacoes.models.Professor;
import com.plataforma.explicacoes.repositories.ProfessorRepo;
import com.plataforma.explicacoes.repositories.UniversidadeRepo;
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

    @Autowired
    private UniversidadeRepo universidadeRepo;


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



    public Optional<Professor> createHorario(Map<String,String> jsonHorario) throws ProfessorDoesNotExistException {
        Optional<Professor> optionalProfessor = this.findById(Long.parseLong(jsonHorario.get("idProfessor")));
        if (optionalProfessor.isEmpty()) {
            throw new ProfessorDoesNotExistException("Professor inexistente");
        }
        checkSobreposicao(optionalProfessor,Integer.parseInt(jsonHorario.get("dia")),LocalTime.parse(jsonHorario.get("hInicio")),LocalTime.parse(jsonHorario.get("hFim")));
        optionalProfessor.get().addHorario(new Horario( DayOfWeek.of(Integer.parseInt(jsonHorario.get("dia"))), LocalTime.parse(jsonHorario.get("hInicio")), LocalTime.parse(jsonHorario.get("hFim"))));
        universidadeRepo.save(optionalProfessor.get().getCadeiras().iterator().next().getCurso().getFaculdade().getUniversidade());
        return Optional.of(optionalProfessor.get());
    }

    public boolean checkSobreposicao (Optional<Professor> optionalProfessor,Integer dia, LocalTime hInicio, LocalTime hFim){
        for (Horario horario : optionalProfessor.get().getHorarios()) {
            if (horario.getDia().getValue() == dia)
                if (hInicio.isBefore(horario.getHFim()) && hFim.isAfter(horario.getHInicio()))
                    return false;
        }
        return true;
    }
}
