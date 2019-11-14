package com.plataforma.explicacoes.repositories;

import com.plataforma.explicacoes.models.Idioma;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdiomaRepo extends CrudRepository<Idioma,Long> {
}
