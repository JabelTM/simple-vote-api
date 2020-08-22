package com.example.simplevoteapi.domain.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class VoteSessionResponse {

    private long id;
    private int sessionTimeInMinutes;
    private VoteAgendaResponse agenda;
    private boolean sessionOpen;
    private LocalDateTime sessionStart;
    private LocalDateTime sessionEnd;

}
