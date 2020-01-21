package com.plataforma.explicacoes.repositories;

import com.plataforma.explicacoes.models.Professor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfessorRepo extends CrudRepository<Professor,Long> {
    Optional<Professor> findByNome(String nome);
    Optional<Professor> findById(Long id);
    Optional<Professor> findByNum(int num);
}
