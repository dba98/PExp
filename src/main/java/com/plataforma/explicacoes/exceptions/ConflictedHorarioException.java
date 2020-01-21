package com.plataforma.explicacoes.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Conflito no Horário")
public class ConflictedHorarioException extends Throwable {
    public ConflictedHorarioException(String s) {
        super(s);
    }
}