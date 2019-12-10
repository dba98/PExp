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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

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



    // -------------  Exception --------------------
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Faculdade already exits")
    private static class FaculdadeAlreadyExistsException extends RuntimeException {
        private FaculdadeAlreadyExistsException(String name) {
            super("A faculdade with name: "+name+"already exists");
        }
    }

}
