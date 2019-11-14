package com.plataforma.explicacoes.repositories;

import com.plataforma.explicacoes.models.Faculdade;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FaculdadeRepo extends CrudRepository<Faculdade,Long> {
}
