package com.example.simplevoteapi.domain.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class VoteAgendaResponse {

    private long id;
    private String description;

}
