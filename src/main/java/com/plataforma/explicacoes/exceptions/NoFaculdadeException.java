package com.plataforma.explicacoes.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus( value = HttpStatus.NOT_FOUND, reason = "No such Faculdade")
public class NoFaculdadeException extends Throwable{

    private Logger logger= LoggerFactory.getLogger(this.getClass());

   public NoFaculdadeException(Long id) {
       super("No such Faculdade with id: "+id);
       logger.error("No such Faculdade with id: "+id);
    }

    public NoFaculdadeException(String name) {
        super("No such Faculdade with name: "+name);
        logger.error("No such Faculdade with name: "+name);
    }
}
