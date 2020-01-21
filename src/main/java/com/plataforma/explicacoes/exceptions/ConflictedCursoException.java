package com.plataforma.explicacoes.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Cadeira already exits")
public class ConflictedCursoException extends RuntimeException{

    private Logger logger= LoggerFactory.getLogger(this.getClass());

    public ConflictedCursoException(String name) {
        super("A curso with name: "+name+"already exists");
        logger.error("A curso with name: "+name+"already exists");
    }
}
