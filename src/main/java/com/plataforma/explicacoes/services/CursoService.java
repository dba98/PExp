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



    public Optional<Curso> createCurso (Curso jsonCurso){

        Optional<Faculdade> optionalFaculdade = faculdadeService.findById(jsonCurso.getFaculdade().getId());
        String name = jsonCurso.getNome();
        Integer codigo = jsonCurso.getCodigo();
        Optional<Curso> optionalCurso = cursoRepo.findByCodigo(codigo);

        if(optionalFaculdade.isEmpty()){
            return Optional.empty();
        }

        if(optionalCurso.isPresent()){
            return Optional.empty();
        }

        Curso curso= new Curso(name,codigo);
        curso.associateFaculdade(optionalFaculdade.get());

        this.cursoRepo.save(curso);
        return Optional.of(curso);
    }
}
