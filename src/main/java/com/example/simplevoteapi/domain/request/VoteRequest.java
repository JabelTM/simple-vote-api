package com.example.simplevoteapi.domain.request;

import com.example.simplevoteapi.domain.Vote;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VoteRequest {

    private long voteAgendaId;
    private Vote vote;

}
