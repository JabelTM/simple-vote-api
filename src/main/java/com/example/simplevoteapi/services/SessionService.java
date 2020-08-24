package com.example.simplevoteapi.services;

import com.example.simplevoteapi.domain.Agenda;
import com.example.simplevoteapi.domain.Session;
import com.example.simplevoteapi.domain.request.CreateSessionRequest;
import com.example.simplevoteapi.domain.response.SessionResponse;
import com.example.simplevoteapi.mapper.SessionResponseMapper;
import com.example.simplevoteapi.repository.SessionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Slf4j
@Service
public class SessionService {

    private static final int DEFAULT_SESSION_TIME = 1;

    @Autowired
    private AgendaService agendaService;

    @Autowired
    private SessionRepository repository;

    @Autowired
    private SessionResponseMapper sessionResponseMapper;

    @Autowired
    private SessionControlService sessionControlService;


    public SessionResponse createAndOpenSession(CreateSessionRequest request) {
        log.info("[SessionController.createAndOpenSession] Inicio do processo de abertura de sessão de votação.");
        Session session = create(request);

        session = sessionControlService.start(session);

        log.info("[SessionController.createAndOpenSession] Fim do processo de abertura de sessão de votação.");
        return sessionResponseMapper.map(session);
    }

    private Session create(CreateSessionRequest request) {
        Agenda agenda = agendaService.findById(request.getAgendaId());

        Session session = new Session();
        session.setAgenda(agenda);
        session.setSessionOpen(false);
        session.setSessionTimeInMinutes(request.getSessionTimeInMinutes() > 0? request.getSessionTimeInMinutes() : DEFAULT_SESSION_TIME);

        return repository.save(session);
    }

    public List<SessionResponse> getSessions() {
        log.info("[SessionController.getSessions] Buscando todas as sessões.");
        return repository.findAll()
                .stream()
                .map(session -> sessionResponseMapper.map(session))
                .collect(toList());
    }

    public Session save(Session session) {
        return repository.save(session);
    }

    public Session findById(long id) {
        log.info("[SessionController.findById] Buscando dados da sessão {}.", id);
        return repository.findById(id);
    }

}
