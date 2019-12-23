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



    public Optional<Professor> createHorario(Professor professor) throws ProfessorDoesNotExistException {
        Optional<Professor> optionalProfessor = professorRepo.findByNum(professor.getNum());
        if (optionalProfessor.isEmpty()) {
            throw new ProfessorDoesNotExistException("Professor inexistente");
        }
        //Professor auxprofessor = optionalProfessor.get();
        optionalProfessor.get().setHorarios(professor.getHorarios());
        professorRepo.save(optionalProfessor.get());
        return Optional.of(optionalProfessor.get());
    }




    /*public boolean checkSobreposicao (Professor professor, Professor auxprofessor){
        for (Horario horario : auxprofessor.getHorarios()) {
            if (horario.getDia().getValue() == auxprofessor.get)
                if (hInicio.isBefore(horario.getHFim()) && hFim.isAfter(horario.getHInicio()))
                    return false;
        }
        return true;
    }*/
}
