package com.plataforma.explicacoes.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AtendimentoRepo extends CrudRepository<AtendimentoRepo,Long> {
}
