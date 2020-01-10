package com.plataforma.explicacoes.controllers;

import com.plataforma.explicacoes.models.Curso;
import com.plataforma.explicacoes.services.CursoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/curso")
public class CursoController {

    private Logger logger= LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CursoService cursoService;

    @PostMapping(value = "/faculdade", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Curso> createCurso(@RequestBody Curso curso) throws ConflictedCursoException{

        System.out.println(curso);

        Optional<Curso> optionalCurso = this.cursoService.createCurso(curso);
        if (optionalCurso.isEmpty()){
            throw new CursoController.ConflictedCursoException("");
        }

        return ResponseEntity.ok(optionalCurso.get());
    }



    private static class ConflictedCursoException extends Throwable {
        public ConflictedCursoException(String s) { super("Curso Existente");}
    }
}
