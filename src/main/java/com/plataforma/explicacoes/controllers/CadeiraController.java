package com.plataforma.explicacoes.controllers;

import com.plataforma.explicacoes.exceptions.CadeiraAlreadyExistsException;
import com.plataforma.explicacoes.exceptions.NoCadeiraException;
import com.plataforma.explicacoes.models.Cadeira;
import com.plataforma.explicacoes.services.CadeiraService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/cadeira")
public class CadeiraController {

    private Logger logger= LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CadeiraService cadeiraService;

    @RequestMapping (method = RequestMethod.GET)
    public ResponseEntity<Iterable<Cadeira>> getAllCadeira(){
        this.logger.info("Received a get request");
        return ResponseEntity.ok(this.cadeiraService.findAll());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public  ResponseEntity<Cadeira> getCadeiraById (@PathVariable("id") Long id) throws NoCadeiraException {
        this.logger.info("Received a get request");

        Optional<Cadeira> optionalCadeira = this.cadeiraService.findById(id);
        if(optionalCadeira.isPresent())
            return ResponseEntity.ok(optionalCadeira.get());
        throw new NoCadeiraException(id);
    }

    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
    public  ResponseEntity<Cadeira> getCadeiraByName (@PathVariable("name") String name) throws NoCadeiraException {
        this.logger.info("Received a get request");

        Optional<Cadeira> optionalCadeira = this.cadeiraService.findByName(name);
        if(optionalCadeira.isPresent())
            return ResponseEntity.ok(optionalCadeira.get());
        throw new NoCadeiraException(name);
    }

    @PostMapping(value = "/{curso}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cadeira> createCadeira (@RequestBody Cadeira cadeira, @PathVariable String curso){
        System.out.println(cadeira);

        Optional <Cadeira> optionalCadeira = this.cadeiraService.createCadeira(cadeira,curso);
        if(optionalCadeira.isPresent())
            return ResponseEntity.ok(optionalCadeira.get());
        throw new CadeiraAlreadyExistsException(cadeira.getName());
    }


}
