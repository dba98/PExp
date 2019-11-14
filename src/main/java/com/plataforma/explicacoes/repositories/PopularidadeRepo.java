package com.plataforma.explicacoes.repositories;

import com.plataforma.explicacoes.models.Popularidade;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PopularidadeRepo extends CrudRepository<Popularidade,Long> {
}
