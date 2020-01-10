package com.plataforma.explicacoes.exceptions;

public class NoFaculdadeException extends Throwable {
    public NoFaculdadeException (String name){
        super("Faculdade "+name+" not exists");
    }

    public NoFaculdadeException (Long id){
        super("Faculdade with id "+id+" not exists");
    }
}
