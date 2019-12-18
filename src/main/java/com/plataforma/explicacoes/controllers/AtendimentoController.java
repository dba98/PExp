package com.plataforma.explicacoes.controllers;

import com.plataforma.explicacoes.models.Atendimento;
import com.plataforma.explicacoes.services.AtendimentoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/atendimento")
public class AtendimentoController {

    private Logger logger= LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AtendimentoService atendimentoService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Atendimento> createAtendimento(@RequestBody Map<String, String> jsonAtendimento) throws AtendimentoController.ConflictedAtendimentoException {
        System.out.println(jsonAtendimento);


        Optional<Atendimento> optionalAtendimento = this.atendimentoService.createAtendimento(jsonAtendimento);
        if (optionalAtendimento.isEmpty()) {
            throw new AtendimentoController.ConflictedAtendimentoException("");
        }
        return ResponseEntity.ok(optionalAtendimento.get());

    }




    private static class ConflictedAtendimentoException extends Throwable {
        public ConflictedAtendimentoException(String s) {
            super("Atendimento Sobreposto");
        }
    }


}
