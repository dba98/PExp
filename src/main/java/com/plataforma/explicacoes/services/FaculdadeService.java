package com.plataforma.explicacoes.services;

import com.plataforma.explicacoes.models.Faculdade;
import com.plataforma.explicacoes.repositories.FaculdadeRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class FaculdadeService {

    @Autowired
    private FaculdadeRepo faculdadeRepo;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public Set<Faculdade> findAll(){
        Set<Faculdade> faculdades = new HashSet<>();
        for(Faculdade f1 : this.faculdadeRepo.findAll())
            faculdades.add(f1);
        return faculdades;
    }

    public Optional<Faculdade> finsById(Long id){
        return this.faculdadeRepo.findById(id);
    }

    public Optional<Faculdade> findByName( String name){
        return  this.faculdadeRepo.findByName(name);
    }

    public Optional<Faculdade> createFaculdade(Faculdade faculdade){
        this.logger.info("Estou aqui");
        this.logger.info(faculdade.toString());
        Optional<Faculdade> optionalFaculdade = this.faculdadeRepo.findByName(faculdade.getName());
        if(optionalFaculdade.isEmpty())
            return  Optional.of(this.faculdadeRepo.save(faculdade));
        return Optional.empty();
    }
}
