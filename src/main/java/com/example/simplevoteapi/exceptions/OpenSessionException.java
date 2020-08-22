package com.example.simplevoteapi.exceptions;

public class OpenSessionException extends RuntimeException {

    private static final String DEFAULT_EXCEPTION_MESSAGE = "Já existe uma sessão de votação aberta.";

    public OpenSessionException() {
        super(DEFAULT_EXCEPTION_MESSAGE);
    }

}
