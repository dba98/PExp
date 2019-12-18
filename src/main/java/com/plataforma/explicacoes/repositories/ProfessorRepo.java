package com.plataforma.explicacoes.repositories;

import com.plataforma.explicacoes.models.Professor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfessorRepo extends CrudRepository<Professor,Long> {
    Optional<Professor> findByName(String name);
    Optional<Professor> findById(Long id);
}
