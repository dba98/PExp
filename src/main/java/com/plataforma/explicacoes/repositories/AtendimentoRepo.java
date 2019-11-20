package com.plataforma.explicacoes.repositories;

import com.plataforma.explicacoes.models.Atendimento;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AtendimentoRepo extends CrudRepository<Atendimento,Long> {
}
