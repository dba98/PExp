package com.plataforma.explicacoes.services;

import com.plataforma.explicacoes.models.Cadeira;
import com.plataforma.explicacoes.models.Curso;
import com.plataforma.explicacoes.repositories.CadeiraRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class CadeiraService {

    @Autowired
    private CadeiraRepo cadeiraRepo;

    @Autowired
    private CursoService cursoService;

    public Set<Cadeira> findAll(){
        Set<Cadeira> cadeiras = new HashSet<>();
        for (Cadeira c1 : this.cadeiraRepo.findAll())
            cadeiras.add(c1);
        return cadeiras;
    }

    public Optional<Cadeira> findById(Long id){
        return this.cadeiraRepo.findById(id);
    }

    public Optional<Cadeira> findByName( String name){
        return this.cadeiraRepo.findByName(name);
    }

    public Optional<Cadeira> createCadeira (Cadeira cadeira, String curso){

        String name = cadeira.getName();
        Integer codigo = cadeira.getCodigo();

        Optional<Curso> optionalCurso = cursoService.findByName(curso);
        Optional<Cadeira> optionalCadeira = this.cadeiraRepo.findByCodigo(codigo);

        if(optionalCurso.isEmpty()){ return Optional.empty(); }
        if(optionalCadeira.isPresent()){ return optionalCadeira; }

        Cadeira cadeira1= new Cadeira(name,codigo);
        cadeira1.associateCurso(optionalCurso.get());

        this.cadeiraRepo.save(cadeira1);
        return Optional.of(cadeira1);

    }
}
