package com.plataforma.explicacoes.repositories;

import com.plataforma.explicacoes.models.Faculdade;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FaculdadeRepo extends CrudRepository<Faculdade,Long> {
    Optional<Faculdade> findByNome(String nome);
}
