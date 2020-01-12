package com.plataforma.explicacoes.services;

import com.plataforma.explicacoes.models.Faculdade;
import com.plataforma.explicacoes.repositories.FaculdadeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@Service
public class FaculdadeService {

    @Autowired
    private FaculdadeRepo faculdadeRepo;

    public Set<Faculdade> findAll(){
        Set<Faculdade> faculdades = new HashSet<>();
        for(Faculdade f1 : this.faculdadeRepo.findAll())
            faculdades.add(f1);
        return faculdades;
    }

    public Optional<Faculdade> findById(Long id){
        return this.faculdadeRepo.findById(id);
    }

    public Optional<Faculdade> findByNome( String name){
        return  this.faculdadeRepo.findByNome(name);
    }

    public Optional<Faculdade> createFaculdade(Faculdade faculdade){
        Optional<Faculdade> optionalFaculdade = this.faculdadeRepo.findByNome(faculdade.getNome());
        if(optionalFaculdade.isEmpty())
            return  Optional.of(this.faculdadeRepo.save(faculdade));
        return Optional.empty();
    }
}
