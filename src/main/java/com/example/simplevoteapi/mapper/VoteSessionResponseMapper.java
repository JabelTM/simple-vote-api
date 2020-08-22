package com.example.simplevoteapi.mapper;

import com.example.simplevoteapi.domain.VoteSession;
import com.example.simplevoteapi.domain.response.VoteAgendaResponse;
import com.example.simplevoteapi.domain.response.VoteSessionResponse;
import org.springframework.stereotype.Component;

@Component
public class VoteSessionResponseMapper {

    public VoteSessionResponse map(VoteSession session) {
        VoteSessionResponse response = new VoteSessionResponse();
        response.setAgenda(new VoteAgendaResponse(session.getAgenda().getId(), session.getAgenda().getDescription()));
        response.setId(session.getId());
        response.setSessionStart(session.getSessionStart());
        response.setSessionOpen(session.isSessionOpen());
        response.setSessionEnd(session.getSessionEnd());
        response.setSessionTimeInMinutes(session.getSessionTimeInMinutes());
        return response;
    }

}
