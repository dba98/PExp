package com.plataforma.explicacoes.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Aluno already exits")
public class AlunoAlreadyExistsException extends RuntimeException{

    private Logger logger= LoggerFactory.getLogger(this.getClass());
    public AlunoAlreadyExistsException(String name) {
        super("A aluno with name: "+name+"already exists");
        logger.error("A aluno with name: "+name+"already exists");
    }

}
