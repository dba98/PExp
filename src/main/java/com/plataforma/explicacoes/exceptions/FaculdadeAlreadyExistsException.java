package com.plataforma.explicacoes.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Faculdade already exits")
public class FaculdadeAlreadyExistsException extends RuntimeException{
    private Logger logger= LoggerFactory.getLogger(this.getClass());
    public FaculdadeAlreadyExistsException(String name) {
        super("A faculdade with name: "+name+"already exists");
        logger.error("A faculdade with name: "+name+"already exists");
    }
}
