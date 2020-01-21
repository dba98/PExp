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
import org.springframework.web.bind.annotation.*;

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
        Optional<Professor> optionalProfessor = this.professorService.findByNome(name);
        if(optionalProfessor.isPresent()){
            return ResponseEntity.ok(optionalProfessor.get());
        }
        throw new ProfessorDoesNotExistException(name);

    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Professor> createProfessor(@RequestBody Professor professor) throws ProfessorAlreadyExistException {
        Optional<Professor> optionalProfessor = this.professorService.createProfessor(professor);

        if(optionalProfessor.isPresent())
            return ResponseEntity.ok(optionalProfessor.get());
        throw  new ProfessorAlreadyExistException(professor.getNome());
    }

    @GetMapping(value = "/search",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<Professor>> searchProfessors(@RequestParam Map<String,String> query){
        System.out.println(query);
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

    @PutMapping(value = "/{curso}")
    public ResponseEntity<Professor> associateCurso(@RequestBody Professor professor, @PathVariable String curso) throws ProfessorDoesNotExistException {
        Optional<Professor> optionalProfessor = this.professorService.associateCurso(professor, curso);

        if(optionalProfessor.isPresent())
            return ResponseEntity.ok(optionalProfessor.get());
        throw new ProfessorDoesNotExistException(professor.getNome());

    }


}
