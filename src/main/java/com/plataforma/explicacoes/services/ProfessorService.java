package com.plataforma.explicacoes.services;

import com.plataforma.explicacoes.models.Professor;
import com.plataforma.explicacoes.repositories.ProfessorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
