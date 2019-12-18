package com.plataforma.explicacoes.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
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
        this.logger.info("Received a get request");
        return ResponseEntity.ok(this.professorService.findAll());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Professor> getProfessorById(@PathVariable("id") Long id) throws NoProfessorException {
        this.logger.info("Received a get request");

        Optional<Professor> optionalProfessor = this.professorService.findById(id);
        if (optionalProfessor.isPresent()) {
            return ResponseEntity.ok(optionalProfessor.get());
        }
        throw new ProfessorController.NoProfessorException(id);
    }

    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Professor> createProfessor(@RequestBody Professor professor) throws ProfessorAlreadyExistsException {
        Optional<Professor> optionalProfessor = this.professorService.createProfessor(professor);
        if (optionalProfessor.isEmpty()) {
            throw new ProfessorAlreadyExistsException(professor.getName());
        }
        return ResponseEntity.ok(optionalProfessor.get());
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Professor> createHorario(@RequestBody Map<String, String> jsonHorario) throws ConflictedHorarioException {
        System.out.println(jsonHorario);


        Optional<Professor> optionalProfessor = this.professorService.createHorario(jsonHorario);
        if (optionalProfessor.isEmpty()) {
            throw new ConflictedHorarioException("");
        }
        return ResponseEntity.ok(optionalProfessor.get());
    }

    private class NoProfessorException extends Throwable {
        public NoProfessorException(Long id) {
            super("No such Professor with id: " + id);
        }
    }


    private class ProfessorAlreadyExistsException extends Throwable {
        public ProfessorAlreadyExistsException(String name) {
            super("Professor Already Exists");
        }
    }

    private class ConflictedHorarioException extends Throwable {
        public ConflictedHorarioException(String s) {
            super("Horario Sobreposto");
        }
    }
}
