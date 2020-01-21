package com.plataforma.explicacoes.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus( value = HttpStatus.NOT_FOUND, reason = "No such Aluno")
public class NoAlunoException extends Throwable{

    private Logger logger= LoggerFactory.getLogger(this.getClass());

    public NoAlunoException(Long id) {
        super("No such Aluno with id: "+id);
        logger.error("No such Aluno with id: "+id);
    }

    public NoAlunoException(String name) {
        super("No such Aluno with name: "+name);
        logger.error("No such Aluno with name: "+name);
    }
}
