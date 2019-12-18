package com.plataforma.explicacoes.services;

import com.plataforma.explicacoes.models.Cadeira;
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

    public Optional<Cadeira> createCadeira (Cadeira cadeira){
        Optional<Cadeira> optionalCadeira = this.cadeiraRepo.findByName(cadeira.getName());
        if(optionalCadeira.isEmpty())
            return Optional.of(this.cadeiraRepo.save(cadeira));
        return Optional.empty();
    }
}
