package com.example.simplevoteapi.services;

import com.example.simplevoteapi.domain.Vote;
import com.example.simplevoteapi.domain.Session;
import com.example.simplevoteapi.domain.request.VoteRequest;
import com.example.simplevoteapi.exceptions.VoteException;
import com.example.simplevoteapi.mapper.VoteMapper;
import com.example.simplevoteapi.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoteService {

    @Autowired
    private VoteSessionService voteSessionService;

    @Autowired
    private VoteAgendaService voteAgendaService;

    @Autowired
    private VoteMapper voteMapper;

    @Autowired
    private VoteRepository voteRepository;

    public void vote(VoteRequest voteRequest) {
        Session session = voteSessionService.getOpenSession();

        validateAgenda(voteRequest, session);

        Vote vote = voteMapper.map(voteRequest, session.getAgenda());

        validateVote(vote, session);

        voteRepository.save(vote);

        voteSessionService.updateOpenSession();
    }

    private void validateVote(Vote vote, Session session) {
        boolean userAlreadyVote = session
                .getAgenda()
                .getVotes()
                .stream()
                .anyMatch(v -> v.getUser().equals(vote.getUser()));

        if (userAlreadyVote) {
            throw new VoteException(VoteException.USER_ALREADY_VOTED);
        }
    }

    private void validateAgenda(VoteRequest vote, Session session) {
        if (session == null || session.getAgenda().getId() != vote.getVoteAgendaId()) {
            throw new VoteException(VoteException.AGENDA_IS_CLOSED);
        }
    }

    public List<Vote> getVotes(long agendaId) {
        return voteAgendaService.findById(agendaId)
                .getVotes();
    }
}
