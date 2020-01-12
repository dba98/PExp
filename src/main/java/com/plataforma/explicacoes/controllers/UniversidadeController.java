package com.plataforma.explicacoes.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.plataforma.explicacoes.models.Universidade;
import com.plataforma.explicacoes.services.UniversidadeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;

@Controller
@RequestMapping("/universidade")
public class UniversidadeController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private ObjectMapper objectMapper;

    @Autowired
    private UniversidadeService universidadeService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Universidade> getUniversidade(){
        this.logger.info("Received a general get request");
        return ResponseEntity.ok(this.universidadeService.getUniversidade());
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Universidade> sendUniversidade(){
           this.logger.info("Received a post request");
           Optional<Universidade> universidadeOptional= this.universidadeService.sendUniversidade();
           if(universidadeOptional.isEmpty()){
               return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
           }
           return  ResponseEntity.ok(universidadeOptional.get());
    }
}
