package com.example.simplevoteapi.exceptions;

public class AgendaException extends RuntimeException {

    public static final String AGENDA_NOT_FIND = "Pauta não encontrada.";

    public AgendaException(String message) {
        super(message);
    }
    
}
