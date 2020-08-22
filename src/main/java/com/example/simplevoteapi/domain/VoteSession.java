package com.example.simplevoteapi.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class VoteSession {

    private long id;
    private int sessionTimeInMinutes;
    private VoteAgenda agenda;
    private boolean sessionOpen;
    private LocalDateTime sessionStart;
    private LocalDateTime sessionEnd;

}
