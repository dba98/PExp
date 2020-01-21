package com.plataforma.explicacoes.controllers;


import com.plataforma.explicacoes.exceptions.FaculdadeAlreadyExistsException;
import com.plataforma.explicacoes.exceptions.NoFaculdadeException;
import com.plataforma.explicacoes.models.Faculdade;
import com.plataforma.explicacoes.services.FaculdadeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Optional;

@Controller
@RequestMapping("/faculdade")
public class FaculdadeController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private FaculdadeService faculdadeService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Faculdade> createFaculdade (@RequestBody Faculdade faculdade) throws FaculdadeAlreadyExistsException{

        this.logger.info(faculdade.toString());
        Optional<Faculdade> optionalFaculdade = this.faculdadeService.createFaculdade(faculdade);
        if(optionalFaculdade.isPresent())
            return ResponseEntity.ok(optionalFaculdade.get());
        throw new FaculdadeAlreadyExistsException(faculdade.getNome());
    }

    @RequestMapping (method = RequestMethod.GET)
    public ResponseEntity<Iterable<Faculdade>> getAllFaculdade(){
        this.logger.info("Received a get request");
        return ResponseEntity.ok(this.faculdadeService.findAll());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public  ResponseEntity<Faculdade> getFaculdadeById (@PathVariable("id") Long id) throws NoFaculdadeException {
        this.logger.info("Received a get request");

        Optional<Faculdade> optionalCadeira = this.faculdadeService.findById(id);
        if(optionalCadeira.isPresent())
            return ResponseEntity.ok(optionalCadeira.get());
        throw new NoFaculdadeException(id);
    }

    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
    public  ResponseEntity<Faculdade> getFaculdadeByName (@PathVariable("name") String name) throws NoFaculdadeException {
        this.logger.info("Received a get request");

        Optional<Faculdade> optionalFaculdade = this.faculdadeService.findByNome(name);
        if(optionalFaculdade.isPresent())
            return ResponseEntity.ok(optionalFaculdade.get());
        throw new NoFaculdadeException(name);
    }


}
