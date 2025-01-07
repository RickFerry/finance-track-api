package com.api.exception;

public class PessoaInexistenteOuInativaException extends RuntimeException {
    public PessoaInexistenteOuInativaException(String message) {
        super(message);
    }
}
