package com.example.simplevoteapi.exceptions;

public class VoteException extends RuntimeException {

    public static final String AGENDA_IS_CLOSED = "Pauta não foi aberta para votação ou já está encerrada.";
    public static final String USER_ALREADY_VOTED = "O usuario já possui um voto registrado na pauta.";

    public VoteException(String message) {
        super(message);
    }

}
