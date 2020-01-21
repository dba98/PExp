package com.plataforma.explicacoes.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus( value = HttpStatus.NOT_FOUND, reason = "No such Professor")
public class ProfessorDoesNotExistException extends Throwable {

    private Logger logger= LoggerFactory.getLogger(this.getClass());
    public ProfessorDoesNotExistException(String name) {
        super("No such Professor with name: "+name);
        logger.error("No such Professor with name: "+name);
    }

    public ProfessorDoesNotExistException(Long id) {
        super("No such Professor with id: "+id);
        logger.error("No such Professor with id: "+id);
    }
}