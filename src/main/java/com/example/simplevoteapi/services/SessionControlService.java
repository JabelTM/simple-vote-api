package com.example.simplevoteapi.services;

import com.example.simplevoteapi.domain.Session;
import com.example.simplevoteapi.exceptions.OpenSessionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.ScheduledFuture;

@Service
public class SessionControlService {

    private ScheduledFuture<?> schedulerFuture;
    private Session openedSession;

    @Autowired
    private TaskScheduler task;

    @Autowired
    private SessionService sessionService;

    public Session start(Session session) {
        if (schedulerFuture != null && openedSession != null) {
            throw new OpenSessionException();
        }

        openedSession = session;

        openedSession.setSessionStart(LocalDateTime.now());

        openedSession.setSessionOpen(true);

        Instant sessionCloseInstant = getScheduleTime(openedSession);
        schedulerFuture = task.schedule(scheduleCloseSession(openedSession), sessionCloseInstant);

        openedSession.setSessionEnd(LocalDateTime.ofInstant(sessionCloseInstant, ZoneId.systemDefault()));

        return saveSession(openedSession);
    }

    private Instant getScheduleTime(Session session) {
        return Instant.now()
                .plus(session.getSessionTimeInMinutes(), ChronoUnit.MINUTES);
    }

    private Session saveSession(Session session) {
        return sessionService.save(session);
    }


    private Runnable scheduleCloseSession(Session session) {
        return () -> {
            stopSessionSchedule();
            closeSession(session);
        };
    }

    public Session closeSession(Session session) {
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
        openedSession = sessionService.findById(openedSession.getId());
    }
}
