package com.plataforma.explicacoes.controllers;

import ch.qos.logback.core.net.server.Client;
import com.plataforma.explicacoes.models.Aluno;
import com.plataforma.explicacoes.repositories.AlunoRepo;
import com.plataforma.explicacoes.services.AlunoService;
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
@RequestMapping("/aluno")
public class AlunoController {

    private Logger logger= LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AlunoService alunoService;

    @RequestMapping (method = RequestMethod.GET)
    public ResponseEntity<Iterable<Aluno>> getAllAluno(){
        this.logger.info("Received a get request");

        return ResponseEntity.ok(this.alunoService.findAll());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public  ResponseEntity<Aluno> getAlunoById (@PathVariable("id") Long id) throws NoAlunoException {
        this.logger.info("Received a get request");

        Optional<Aluno> optionalAluno = this.alunoService.findById(id);
        if(optionalAluno.isPresent()){
            return ResponseEntity.ok(optionalAluno.get());
        }
        throw  new NoAlunoException(id);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Aluno> createAluno (@RequestBody Client client){
        Optional <Aluno> optionalAluno = this.alunoService.createClient(client);
        if(optionalAluno.isPresent()){
            return ResponseEntity.ok(optionalAluno.get());
        }

        throw
    }





    // -------------  Exception --------------------
    @ResponseStatus( value = HttpStatus.NOT_FOUND, reason = "No such Aluno")
    private class NoAlunoException extends RuntimeException {

        public NoAlunoException(Long id) {
            super("No such Aluno with id: "+id);
        }
    }

    //@ResponseStatus(value = H)

    
}
