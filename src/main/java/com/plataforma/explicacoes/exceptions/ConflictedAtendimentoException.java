package com.plataforma.explicacoes.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Atendimento Sobreposto")
public class ConflictedAtendimentoException extends RuntimeException{
    public ConflictedAtendimentoException() {
        super("Atendimento Sobreposto");
    }
}
