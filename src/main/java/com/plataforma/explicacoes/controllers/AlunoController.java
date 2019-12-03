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

    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    public  ResponseEntity<Aluno> getAlunoById (@PathVariable("id") Long id) throws NoAlunoException {
        this.logger.info("Received a get request");

        Optional<Aluno> optionalAluno = this.alunoService.findById(id);
        if(optionalAluno.isPresent()){
            return ResponseEntity.ok(optionalAluno.get());
        }
        throw new NoAlunoException(id);
    }

    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
    public  ResponseEntity<Aluno> getAlunoByName (@PathVariable("name") String name) throws NoAlunoException {
        this.logger.info("Received a get request");

        Optional<Aluno> optionalAluno = this.alunoService.findByName(name);
        if(optionalAluno.isPresent()){
            return ResponseEntity.ok(optionalAluno.get());
        }
        throw new NoAlunoException(name);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Aluno> createAluno (@RequestBody Aluno aluno){
        Optional <Aluno> optionalAluno = this.alunoService.createAluno(aluno);
        if(optionalAluno.isPresent()){
            return ResponseEntity.ok(optionalAluno.get());
        }

        throw new AlunoAlreadyExistsException(aluno.getName());
    }




    // -------------  Exception --------------------
    @ResponseStatus( value = HttpStatus.NOT_FOUND, reason = "No such Aluno")
    private static class NoAlunoException extends RuntimeException {

        private NoAlunoException(Long id) {
            super("No such Aluno with id: "+id);
        }

        private NoAlunoException(String name) {
            super("No such Aluno with name: "+name);
        }
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Aluno already exits")
    private static class AlunoAlreadyExistsException extends RuntimeException {

        private AlunoAlreadyExistsException(String name) {
            super("A aluno with name: "+name+"already exists");
        }
    }

    
}
