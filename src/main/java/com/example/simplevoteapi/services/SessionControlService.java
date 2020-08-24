package com.example.simplevoteapi.services;

import com.example.simplevoteapi.domain.Session;
import com.example.simplevoteapi.exceptions.OpenSessionException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.ScheduledFuture;

@Slf4j
@Service
public class SessionControlService {

    private ScheduledFuture<?> schedulerFuture;
    private Session openedSession;

    @Autowired
    private TaskScheduler task;

    @Autowired
    private SessionService sessionService;

    public Session start(Session session) {
        log.info("[SessionControlService.start] Inicio do processo de abertura de sessão.");
        validateHasOpenSession();

        openedSession = session;

        openedSession.setSessionStart(LocalDateTime.now());

        openedSession.setSessionOpen(true);

        Instant sessionCloseInstant = getScheduleTime(openedSession);
        schedulerFuture = task.schedule(scheduleCloseSession(openedSession), sessionCloseInstant);

        openedSession.setSessionEnd(LocalDateTime.ofInstant(sessionCloseInstant, ZoneId.systemDefault()));

        log.info("[SessionControlService.start] Sessão aberta.");

        return saveSession(openedSession);
    }

    private void validateHasOpenSession() {
        log.info("[SessionControlService.validateHasOpenSession] Validando se não possui sessão aberta.");
        if (schedulerFuture != null && openedSession != null) {
            throw new OpenSessionException();
        }
    }

    private Instant getScheduleTime(Session session) {
        return Instant.now()
                .plus(session.getSessionTimeInMinutes(), ChronoUnit.MINUTES);
    }

    private Session saveSession(Session session) {
        log.info("[SessionControlService.saveSession] Registra sessão aberta.");
        return sessionService.save(session);
    }


    private Runnable scheduleCloseSession(Session session) {
        log.info("[SessionControlService.scheduleCloseSession] Encerrando sessão {}.", session.getId());
        return () -> {
            stopSessionSchedule();
            closeSession(session);
        };
    }

    public Session closeSession(Session session) {
        log.info("[SessionControlService.closeSession] Atualiza status sessão {} como encerrada.", session.getId());
        session.setSessionEnd(LocalDateTime.now());
        session.setSessionOpen(false);
        return saveSession(session);
    }

    private void stopSessionSchedule() {
        schedulerFuture.cancel(false);
        schedulerFuture = null;
        openedSession = null;
    }

    public Session getOpenSession() {
        return openedSession;
    }

    public void updateOpenSession() {
        log.info("[SessionControlService.updateOpenSession] Atualiza sessão aberta.");
        openedSession = sessionService.findById(openedSession.getId());
    }
}
