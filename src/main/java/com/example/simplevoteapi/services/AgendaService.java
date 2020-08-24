package com.example.simplevoteapi.services;

import com.example.simplevoteapi.domain.Agenda;
import com.example.simplevoteapi.domain.request.CreateAgendaRequest;
import com.example.simplevoteapi.domain.response.AgendaResponse;
import com.example.simplevoteapi.exceptions.AgendaException;
import com.example.simplevoteapi.repository.AgendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class AgendaService {

    @Autowired
    private AgendaRepository repository;

    public AgendaResponse create(CreateAgendaRequest request) {
        Agenda agenda = new Agenda();
        agenda.setDescription(request.getDescription());

        agenda = repository.save(agenda);
        return AgendaResponse.builder()
                .id(agenda.getId())
                .description(agenda.getDescription())
                .build();
    }

    public List<AgendaResponse> getVoteAgendas() {
        return repository.findAll()
                .stream()
                .map(voteAgenda -> AgendaResponse.builder()
                                        .id(voteAgenda.getId())
                                        .description(voteAgenda.getDescription())
                                        .build())
                .collect(toList());
    }

    public Agenda findById(long id) {
        Agenda agenda = repository.findById(id);

        if (agenda == null) {
            throw new AgendaException(AgendaException.AGENDA_NOT_FIND);
        }

        return agenda;
    }

}
