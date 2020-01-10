package com.plataforma.explicacoes.controllers;


import com.plataforma.explicacoes.models.Faculdade;
import com.plataforma.explicacoes.services.FaculdadeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/faculdade")
public class FaculdadeController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private FaculdadeService faculdadeService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Faculdade> createFaculdade (@RequestBody Faculdade faculdade){

        this.logger.info(faculdade.toString());
        Optional<Faculdade> optionalFaculdade = this.faculdadeService.createFaculdade(faculdade);
        if(optionalFaculdade.isPresent())
            return ResponseEntity.ok(optionalFaculdade.get());
        throw new FaculdadeAlreadyExistsException(faculdade.getName());
    }

    @RequestMapping (method = RequestMethod.GET)
    public ResponseEntity<Iterable<Faculdade>> getAllFaculdade(){
        this.logger.info("Received a get request");
        return ResponseEntity.ok(this.faculdadeService.findAll());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public  ResponseEntity<Faculdade> getFaculdadeById (@PathVariable("id") Long id) throws FaculdadeController.NoFaculdadeException{
        this.logger.info("Received a get request");

        Optional<Faculdade> optionalCadeira = this.faculdadeService.findById(id);
        if(optionalCadeira.isPresent())
            return ResponseEntity.ok(optionalCadeira.get());
        throw new NoFaculdadeException(id);
    }

    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
    public  ResponseEntity<Faculdade> getFaculdadeByName (@PathVariable("name") String name) throws FaculdadeController.NoFaculdadeException {
        this.logger.info("Received a get request");

        Optional<Faculdade> optionalFaculdade = this.faculdadeService.findByName(name);
        if(optionalFaculdade.isPresent())
            return ResponseEntity.ok(optionalFaculdade.get());
        throw new NoFaculdadeException(name);
    }

    // -------------  Exception --------------------
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Faculdade already exits")
    private static class FaculdadeAlreadyExistsException extends RuntimeException {
        private FaculdadeAlreadyExistsException(String name) {
            super("A faculdade with name: "+name+"already exists");
        }
    }

    @ResponseStatus( value = HttpStatus.NOT_FOUND, reason = "No such Faculdade")
    private static class NoFaculdadeException extends RuntimeException {

        private NoFaculdadeException(Long id) {
            super("No such Faculdade with id: "+id);
        }

        private NoFaculdadeException(String name) {
            super("No such Faculdade with name: "+name);
        }
    }

}
