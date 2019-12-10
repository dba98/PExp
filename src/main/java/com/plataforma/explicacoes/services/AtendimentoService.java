package com.plataforma.explicacoes.services;

import com.plataforma.explicacoes.models.Atendimento;
import com.plataforma.explicacoes.repositories.AtendimentoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AtendimentoService {

    @Autowired
    private AtendimentoRepo atendimentoRepo;

    public Optional <Atendimento> createAtendimento (Atendimento atendimento){
        Optional<Atendimento> optionalAtendimento = this.atendimentoRepo.f
    }


}
