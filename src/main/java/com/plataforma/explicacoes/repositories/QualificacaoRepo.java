package com.plataforma.explicacoes.repositories;

import com.plataforma.explicacoes.models.Qualificacao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QualificacaoRepo extends CrudRepository<Qualificacao,Long> {
}
