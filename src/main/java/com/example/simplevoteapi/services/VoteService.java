package com.example.simplevoteapi.services;

import com.example.simplevoteapi.domain.Vote;
import com.example.simplevoteapi.domain.VoteSession;
import com.example.simplevoteapi.domain.request.VoteRequest;
import com.example.simplevoteapi.exceptions.VoteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoteService {

    @Autowired
    private VoteSessionService voteSessionService;

    @Autowired
    private VoteAgendaService voteAgendaService;

    public void vote(VoteRequest vote) {
        VoteSession session = voteSessionService.getOpenSession();

        validateAgenda(vote, session);

        validateVote(vote, session);

        session.getAgenda().getVotes().add(vote.getVote());

        voteSessionService.update(session);
    }

    private void validateVote(VoteRequest vote, VoteSession session) {
        boolean userAlreadyVote = session
                .getAgenda()
                .getVotes()
                .stream()
                .anyMatch(v -> v.getUser().equals(vote.getVote().getUser()));

        if (userAlreadyVote) {
            throw new VoteException(VoteException.USER_ALREADY_VOTED);
        }
    }

    private void validateAgenda(VoteRequest vote, VoteSession session) {
        if (session == null || session.getAgenda().getId() != vote.getVoteAgendaId()) {
            throw new VoteException(VoteException.AGENDA_IS_CLOSED);
        }
    }

    public List<Vote> getVotes(long agendaId) {
        return voteAgendaService.findById(agendaId)
                .getVotes();
    }
}
