package com.plataforma.explicacoes.repositories;

import com.plataforma.explicacoes.models.Professor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepo extends CrudRepository<Professor,Long> {
}
