package com.example.simplevoteapi.services;

import com.example.simplevoteapi.domain.Session;
import com.example.simplevoteapi.domain.Vote;
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
    private SessionControlService sessionControlService;

    @Autowired
    private AgendaService agendaService;

    @Autowired
    private VoteMapper voteMapper;

    @Autowired
    private VoteRepository voteRepository;

    public void vote(VoteRequest voteRequest) {
        Session session = sessionControlService.getOpenSession();

        validateAgenda(voteRequest, session);

        Vote vote = voteMapper.map(voteRequest, session.getAgenda());

        validateVote(vote, session);

        voteRepository.save(vote);

        sessionControlService.updateOpenSession();
    }

    private void validateVote(Vote vote, Session session) {
        boolean userAlreadyVote = voteRepository
                                    .findAllByAgendaId(session.getAgenda().getId())
                                    .stream()
                                    .anyMatch(v -> v.getUser().equals(vote.getUser()));

        if (userAlreadyVote) {
            throw new VoteException(VoteException.USER_ALREADY_VOTED);
        }
    }

    private void validateAgenda(VoteRequest vote, Session session) {
        if (session == null || session.getAgenda().getId() != vote.getAgendaId()) {
            throw new VoteException(VoteException.AGENDA_IS_CLOSED);
        }
    }

    public List<Vote> getVotes(long agendaId) {
        return voteRepository.findAllByAgendaId(agendaId);
    }
}
