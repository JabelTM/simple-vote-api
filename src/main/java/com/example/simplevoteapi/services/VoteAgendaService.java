package com.example.simplevoteapi.services;

import com.example.simplevoteapi.domain.Agenda;
import com.example.simplevoteapi.domain.request.CreateVoteAgendaRequest;
import com.example.simplevoteapi.domain.response.VoteAgendaResponse;
import com.example.simplevoteapi.exceptions.AgendaException;
import com.example.simplevoteapi.repository.VoteAgendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class VoteAgendaService {

    @Autowired
    private VoteAgendaRepository repository;

    public VoteAgendaResponse create(CreateVoteAgendaRequest request) {
        Agenda agenda = new Agenda();
        agenda.setDescription(request.getDescription());

        agenda = repository.save(agenda);
        return VoteAgendaResponse.builder()
                .id(agenda.getId())
                .description(agenda.getDescription())
                .build();
    }

    public List<VoteAgendaResponse> getVoteAgendas() {
        return repository.findAll()
                .stream()
                .map(voteAgenda -> VoteAgendaResponse.builder()
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
