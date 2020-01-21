package com.plataforma.explicacoes.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Cadeira already exits")
public class CadeiraAlreadyExistsException extends RuntimeException {

    private Logger logger= LoggerFactory.getLogger(this.getClass());

    public CadeiraAlreadyExistsException(String name) {
        super("A cadeira with name: "+name+"already exists");
        logger.error("A cadeira with name: "+name+"already exists");
    }

}
