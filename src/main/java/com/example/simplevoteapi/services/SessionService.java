package com.example.simplevoteapi.services;

import com.example.simplevoteapi.domain.Agenda;
import com.example.simplevoteapi.domain.Session;
import com.example.simplevoteapi.domain.request.CreateSessionRequest;
import com.example.simplevoteapi.domain.response.SessionResponse;
import com.example.simplevoteapi.mapper.SessionResponseMapper;
import com.example.simplevoteapi.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

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
        Session session = create(request);

        session = sessionControlService.start(session);

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
        return repository.findAll()
                .stream()
                .map(session -> sessionResponseMapper.map(session))
                .collect(toList());
    }

    public Session save(Session session) {
        return repository.save(session);
    }

    public Session findById(long id) {
        return repository.findById(id);
    }

    public List<Session> getOpenAgendas() {
        return repository.findAllBySessionOpen(true);
    }

}
