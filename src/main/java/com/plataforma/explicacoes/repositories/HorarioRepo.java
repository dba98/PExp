package com.plataforma.explicacoes.repositories;

import com.plataforma.explicacoes.models.Horario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HorarioRepo extends CrudRepository<Horario,Long> {
}
