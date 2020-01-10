package com.plataforma.explicacoes.repositories;

import com.plataforma.explicacoes.models.Curso;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CursoRepo extends CrudRepository<Curso,Long> {

    Optional<Curso> findByCodigo(Integer codigo);
}
