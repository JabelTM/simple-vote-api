package com.example.simplevoteapi.domain.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
public class VoteAgendaResponse {

    private long id;
    private String description;

}
