package com.example.simplevoteapi.services;

import com.example.simplevoteapi.domain.VoteAgenda;
import com.example.simplevoteapi.domain.request.CreateVoteAgendaRequest;
import com.example.simplevoteapi.domain.response.VoteAgendaResponse;
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
        VoteAgenda voteAgenda = new VoteAgenda();
        voteAgenda.setDescription(request.getDescription());

        voteAgenda = repository.save(voteAgenda);
        return new VoteAgendaResponse(voteAgenda.getId(), voteAgenda.getDescription());
    }

    public List<VoteAgendaResponse> getVoteAgendas() {
        return repository.findAll()
                .stream()
                .map(voteAgenda -> new VoteAgendaResponse(voteAgenda.getId(), voteAgenda.getDescription()))
                .collect(toList());
    }

    public VoteAgenda findById(long id) {
        return repository.findById(id);
    }
}
