package com.example.simplevoteapi.services;

import com.example.simplevoteapi.domain.VoteAgenda;
import com.example.simplevoteapi.domain.VoteSession;
import com.example.simplevoteapi.domain.request.CreateVoteSessionRequest;
import com.example.simplevoteapi.domain.response.VoteSessionResponse;
import com.example.simplevoteapi.exceptions.OpenSessionException;
import com.example.simplevoteapi.mapper.VoteSessionResponseMapper;
import com.example.simplevoteapi.repository.VoteSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.concurrent.ScheduledFuture;

import static java.util.stream.Collectors.toList;

@Service
public class VoteSessionService {

    private static final int DEFAULT_SESSION_TIME = 1;
    private ScheduledFuture<?> schedulerFuture;
    private VoteSession session;

    @Autowired
    private VoteAgendaService voteAgendaService;

    @Autowired
    private VoteSessionRepository repository;

    @Autowired
    private TaskScheduler task;

    @Autowired
    private VoteSessionResponseMapper voteSessionResponseMapper;

    public VoteSessionResponse create(CreateVoteSessionRequest request) {
        if (schedulerFuture != null && session != null) {
            throw new OpenSessionException();
        }

        createAndSetVoteSession(request);

        Instant sessionCloseInstant = getScheduleTime(session);
        schedulerFuture = task.schedule(scheduleCloseSession(session), sessionCloseInstant);

        session.setSessionEnd(LocalDateTime.ofInstant(sessionCloseInstant, ZoneId.systemDefault()));
        repository.save(session);

        return voteSessionResponseMapper.map(session);
    }

    private Instant getScheduleTime(VoteSession session) {
        return Instant.now()
                .plus(session.getSessionTimeInMinutes(), ChronoUnit.MINUTES);
    }

    private Runnable scheduleCloseSession(VoteSession session) {
        return () -> {
            stopSessionSchedule();
            session.setSessionEnd(LocalDateTime.now());
            session.setSessionOpen(false);
            repository.save(session);
        };
    }

    private void stopSessionSchedule() {
        schedulerFuture.cancel(false);
        schedulerFuture = null;
        session = null;
    }

    private void createAndSetVoteSession(CreateVoteSessionRequest request) {
        VoteAgenda agenda = voteAgendaService.findById(request.getAgendaId());

        session = new VoteSession();
        session.setAgenda(agenda);
        session.setSessionOpen(true);
        session.setSessionStart(LocalDateTime.now());
        session.setSessionTimeInMinutes(request.getSessionTimeInMinutes() > 0? request.getSessionTimeInMinutes() : DEFAULT_SESSION_TIME);
    }

    public List<VoteSessionResponse> getSessions() {
        return repository.findAll()
                .stream()
                .map(session -> voteSessionResponseMapper.map(session))
                .collect(toList());
    }
}
