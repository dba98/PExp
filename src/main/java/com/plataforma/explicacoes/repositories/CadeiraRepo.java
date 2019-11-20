package com.plataforma.explicacoes.repositories;

import com.plataforma.explicacoes.models.Cadeira;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CadeiraRepo extends CrudRepository<Cadeira,Long> {
}
