package com.plataforma.explicacoes.exceptions;

public class FaculdadeAlreadyExistsException extends Throwable {
    public FaculdadeAlreadyExistsException(String name){
        super("Faculdade "+name+" already exists");
    }
}
