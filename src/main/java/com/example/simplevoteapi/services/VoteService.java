package com.example.simplevoteapi.services;

import com.example.simplevoteapi.domain.Session;
import com.example.simplevoteapi.domain.Vote;
import com.example.simplevoteapi.domain.request.VoteRequest;
import com.example.simplevoteapi.exceptions.VoteException;
import com.example.simplevoteapi.mapper.VoteMapper;
import com.example.simplevoteapi.repository.VoteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
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
        log.info("[VoteService.vote] Inicio do processo de registro de voto.");
        Session session = sessionControlService.getOpenSession();

        validateAgenda(voteRequest, session);

        Vote vote = voteMapper.map(voteRequest, session.getAgenda());

        validateVote(vote, session);

        voteRepository.save(vote);
        log.info("[VoteService.vote] Voto registrado.");

        sessionControlService.updateOpenSession();
        log.info("[VoteService.vote] Fim do processo de registro de voto.");
    }

    private void validateVote(Vote vote, Session session) {
        log.info("[VoteService.validateVote] Validando voto.");
        boolean userAlreadyVote = voteRepository
                                    .findAllByAgendaId(session.getAgenda().getId())
                                    .stream()
                                    .anyMatch(v -> v.getUser().equals(vote.getUser()));

        if (userAlreadyVote) {
            throw new VoteException(VoteException.USER_ALREADY_VOTED);
        }
    }

    private void validateAgenda(VoteRequest vote, Session session) {
        log.info("[VoteService.validateAgenda] Validando pauta.");
        if (session == null || session.getAgenda().getId() != vote.getAgendaId()) {
            throw new VoteException(VoteException.AGENDA_IS_CLOSED);
        }
    }

    public List<Vote> getVotes(long agendaId) {
        log.info("[VoteService.getVotes] Buscando votos para pauta: {}.", agendaId);
        return voteRepository.findAllByAgendaId(agendaId);
    }
}
