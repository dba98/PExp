package com.plataforma.explicacoes.repositories;

import com.plataforma.explicacoes.models.Universidade;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UniversidadeRepo extends CrudRepository<Universidade,Long> {
}
