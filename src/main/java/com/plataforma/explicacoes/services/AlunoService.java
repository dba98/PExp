package com.plataforma.explicacoes.services;

import com.plataforma.explicacoes.models.Aluno;
import com.plataforma.explicacoes.repositories.AlunoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepo alunoRepo;

    public Set<Aluno> findAll(){
        Set<Aluno> alunos = new HashSet<>();
        for (Aluno a1 : this.alunoRepo.findAll()){
            alunos.add(a1);
        }
        return alunos;
    }

    public Optional<Aluno> findById(Long id){

        return this.alunoRepo.findById(id);

    }


}
