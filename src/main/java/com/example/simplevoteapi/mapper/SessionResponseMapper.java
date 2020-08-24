package com.example.simplevoteapi.mapper;

import com.example.simplevoteapi.domain.Agenda;
import com.example.simplevoteapi.domain.Session;
import com.example.simplevoteapi.domain.response.AgendaResponse;
import com.example.simplevoteapi.domain.response.SessionResponse;
import org.springframework.stereotype.Component;

@Component
public class SessionResponseMapper {

    public SessionResponse map(Session session) {
        Agenda agenda = session.getAgenda();

        return SessionResponse.builder()
                .agenda(
                        AgendaResponse.builder()
                        .id(agenda.getId())
                        .description(agenda.getDescription())
                        .build()
                )
                .id(session.getId())
                .sessionStart(session.getSessionStart())
                .sessionOpen(session.isSessionOpen())
                .sessionEnd(session.getSessionEnd())
                .sessionTimeInMinutes(session.getSessionTimeInMinutes())
                .build();
    }

}
