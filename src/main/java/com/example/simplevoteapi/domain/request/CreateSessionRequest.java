package com.example.simplevoteapi.domain.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CreateSessionRequest {

    @NotNull
    private long agendaId;
    private int sessionTimeInMinutes;

}
