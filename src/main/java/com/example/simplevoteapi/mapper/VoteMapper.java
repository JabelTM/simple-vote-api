package com.example.simplevoteapi.mapper;

import com.example.simplevoteapi.domain.Agenda;
import com.example.simplevoteapi.domain.User;
import com.example.simplevoteapi.domain.Vote;
import com.example.simplevoteapi.domain.request.VoteRequest;
import org.springframework.stereotype.Component;

@Component
public class VoteMapper {

    public Vote map(VoteRequest voteRequest, Agenda agenda) {
        User user = new User();
        user.setId(voteRequest.getUserId());
        user.setCpf(voteRequest.getCpf());

        Vote vote = new Vote();
        vote.setVoteResult(voteRequest.getVote());
        vote.setUser(user);
        vote.setAgenda(agenda);

        return vote;
    }

}
