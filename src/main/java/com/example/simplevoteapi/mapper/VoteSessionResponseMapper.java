package com.example.simplevoteapi.mapper;

import com.example.simplevoteapi.domain.Agenda;
import com.example.simplevoteapi.domain.Session;
import com.example.simplevoteapi.domain.response.VoteAgendaResponse;
import com.example.simplevoteapi.domain.response.VoteSessionResponse;
import org.springframework.stereotype.Component;

@Component
public class VoteSessionResponseMapper {

    public VoteSessionResponse map(Session session) {
        Agenda agenda = session.getAgenda();

        return VoteSessionResponse.builder()
                .agenda(
                        VoteAgendaResponse.builder()
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
