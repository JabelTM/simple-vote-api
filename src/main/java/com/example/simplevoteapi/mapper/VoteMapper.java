package com.example.simplevoteapi.mapper;

import com.example.simplevoteapi.domain.User;
import com.example.simplevoteapi.domain.Vote;
import com.example.simplevoteapi.domain.Agenda;
import com.example.simplevoteapi.domain.request.VoteRequest;
import org.springframework.stereotype.Component;

@Component
public class VoteMapper {

    public Vote map(VoteRequest voteRequest, Agenda agenda) {
        return Vote.builder()
                .vote(voteRequest.getVote())
                .agenda(agenda)
                .user(User.builder()
                        .id(voteRequest.getUserId())
                        .cpf(voteRequest.getCpf())
                        .build())
                .build();
    }

}
