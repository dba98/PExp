package com.plataforma.explicacoes.exceptions;

public class ProfessorAlreadyExistException extends Throwable {
    public ProfessorAlreadyExistException(String name) {
        super("Professor "+name+" already exists");
    }
}
