package com.plataforma.explicacoes.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.plataforma.explicacoes.exceptions.ConflictedHorarioException;
import com.plataforma.explicacoes.exceptions.ProfessorAlreadyExistException;
import com.plataforma.explicacoes.exceptions.ProfessorDoesNotExistException;
import com.plataforma.explicacoes.models.Professor;
import com.plataforma.explicacoes.services.ProfessorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;
import java.util.Optional;


@Controller
@RequestMapping("/professor")
public class ProfessorController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private ObjectMapper objectMapper;

    @Autowired
    private ProfessorService professorService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Iterable<Professor>> getAllProfessores() {
        this.logger.info("Received a general get request");
        return ResponseEntity.ok(this.professorService.findAll());
    }

    @RequestMapping(value = "/{name}",method = RequestMethod.GET)
    public ResponseEntity<Professor> getProfessorByName(@PathVariable("name") String name) throws ProfessorDoesNotExistException {
        this.logger.info("Received a get request with a name");
        Optional<Professor> optionalProfessor = this.professorService.findByName(name);
        if(optionalProfessor.isPresent()){
            return ResponseEntity.ok(optionalProfessor.get());
        }
        throw new ProfessorDoesNotExistException("Professor Inexistente");

    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Professor> createProfessor(@RequestBody Professor professor) throws ProfessorAlreadyExistException {
        Optional<Professor> optionalProfessor = this.professorService.createProfessor(professor);
        if (optionalProfessor.isEmpty()) {
            throw new ProfessorAlreadyExistException(professor.getName());
        }
        return ResponseEntity.ok(optionalProfessor.get());
    }

    @GetMapping(value = "/search",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<Professor>> searchProfessors(@RequestParam Map<String,String> query){
        return ResponseEntity.ok(this.professorService.filterProfessors(query));
    }


    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Professor> createHorario(@RequestBody Professor professor) throws ConflictedHorarioException, ProfessorDoesNotExistException {
        Optional<Professor> optionalProfessor = this.professorService.createHorario(professor);

        if (optionalProfessor.isEmpty()) {
            throw new ConflictedHorarioException("Horario Sobreposto");
        }
        return ResponseEntity.ok(optionalProfessor.get());
    }

    @PutMapping(value = "/curso")
    public ResponseEntity<Professor> associeteCurso(@RequestBody Professor professor) throws ProfessorDoesNotExistException {
        Optional<Professor> optionalProfessor = this.professorService.associeteCurso(professor);

        if(optionalProfessor.isPresent()){
            return ResponseEntity.ok(optionalProfessor.get());
        }else{
            throw new ProfessorDoesNotExistException("Professor ou Curso não encontrado");
        }

    }


}
