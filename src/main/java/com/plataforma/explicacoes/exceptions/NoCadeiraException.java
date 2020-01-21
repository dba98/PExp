package com.plataforma.explicacoes.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus( value = HttpStatus.NOT_FOUND, reason = "No such Cadeira")
public class NoCadeiraException extends Throwable{

    private Logger logger= LoggerFactory.getLogger(this.getClass());

    public NoCadeiraException(Long id) {
        super("No such Cadeira with id: "+id);
        logger.error("No such Cadeira with id: "+id);
    }

    public NoCadeiraException(String name) {
        super("No such Cadeira with name: "+name);
        logger.error("No such Cadeira with name: "+name);
    }
}
