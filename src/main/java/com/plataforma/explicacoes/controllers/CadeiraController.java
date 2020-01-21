package com.plataforma.explicacoes.controllers;

import com.plataforma.explicacoes.models.Cadeira;
import com.plataforma.explicacoes.services.CadeiraService;
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
    public  ResponseEntity<Cadeira> getCadeiraById (@PathVariable("id") Long id) throws CadeiraController.NoCadeiraException {
        this.logger.info("Received a get request");

        Optional<Cadeira> optionalCadeira = this.cadeiraService.findById(id);
        if(optionalCadeira.isPresent())
            return ResponseEntity.ok(optionalCadeira.get());
        throw new NoCadeiraException(id);
    }

    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
    public  ResponseEntity<Cadeira> getCadeiraByName (@PathVariable("name") String name) throws CadeiraController.NoCadeiraException {
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
    // -------------  Exception --------------------
    @ResponseStatus( value = HttpStatus.NOT_FOUND, reason = "No such Cadeira")
    private static class NoCadeiraException extends RuntimeException {

        private NoCadeiraException(Long id) {
            super("No such Cadeira with id: "+id);
        }

        private NoCadeiraException(String name) {
            super("No such Cadeira with name: "+name);
        }
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Cadeira already exits")
    private static class CadeiraAlreadyExistsException extends RuntimeException {

        private CadeiraAlreadyExistsException(String name) {
            super("A cadeira with name: "+name+"already exists");
        }
    }

}
