package com.plataforma.explicacoes.services;

import com.plataforma.explicacoes.models.Curso;
import com.plataforma.explicacoes.models.Faculdade;
import com.plataforma.explicacoes.repositories.CursoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CursoService {

    @Autowired
    private CursoRepo cursoRepo;

    @Autowired
    private FaculdadeService faculdadeService;

    public Optional<Curso> findById(Long id){ return this.cursoRepo.findById(id);}
    public Optional<Curso> findByNome(String name){ return this.cursoRepo.findByNome(name);}

    public Optional<Curso> createCurso (Curso curso,String faculdade){

        Optional<Faculdade> optionalFaculdade = faculdadeService.findByNome(faculdade);
        String name = curso.getNome();
        Integer codigo = curso.getCodigo();
        Optional<Curso> optionalCurso = cursoRepo.findByCodigo(codigo);

        if(optionalFaculdade.isEmpty()){
            return Optional.empty();
        }

        if(optionalCurso.isPresent()){
            return Optional.empty();
        }

        Curso curso1= new Curso(name,codigo);
        curso1.associateFaculdade(optionalFaculdade.get());

        this.cursoRepo.save(curso1);
        return Optional.of(curso1);
    }
}
