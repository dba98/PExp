package com.plataforma.explicacoes.repositories;

import com.plataforma.explicacoes.models.Cadeira;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CadeiraRepo extends CrudRepository<Cadeira,Long> {
    Optional<Cadeira> findByName(String name);
}
