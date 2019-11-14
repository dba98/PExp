package com.plataforma.explicacoes.repositories;

import com.plataforma.explicacoes.models.Aluno;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepo extends CrudRepository<Aluno,Long> {
}
