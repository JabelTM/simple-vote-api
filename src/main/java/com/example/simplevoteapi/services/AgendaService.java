package com.example.simplevoteapi.services;

import com.example.simplevoteapi.domain.Agenda;
import com.example.simplevoteapi.domain.request.CreateAgendaRequest;
import com.example.simplevoteapi.domain.response.AgendaResponse;
import com.example.simplevoteapi.exceptions.AgendaException;
import com.example.simplevoteapi.repository.AgendaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Slf4j
@Service
public class AgendaService {

    @Autowired
    private AgendaRepository repository;

    public AgendaResponse create(CreateAgendaRequest request) {
        log.info("[AgendaService.create] Inicio do processo de criação de pauta.");
        Agenda agenda = new Agenda();
        agenda.setDescription(request.getDescription());

        agenda = repository.save(agenda);
        log.info("[AgendaService.create] Pauta {} registrada.", agenda.getId());
        log.info("[AgendaService.create] Fim do processo de criação de pauta.");

        return AgendaResponse.builder()
                .id(agenda.getId())
                .description(agenda.getDescription())
                .build();
    }

    public List<AgendaResponse> getAgendas() {
        log.info("[AgendaService.getAgendas] Buscando todas as pautas.");
        return repository.findAll()
                .stream()
                .map(voteAgenda -> AgendaResponse.builder()
                                        .id(voteAgenda.getId())
                                        .description(voteAgenda.getDescription())
                                        .build())
                .collect(toList());
    }

    public Agenda findById(long id) {
        log.info("[AgendaService.findById] Buscando dados da pauta {}.", id);
        Agenda agenda = repository.findById(id);

        if (agenda == null) {
            log.error("[AgendaService.findById] Pauta {} não encontrada.", id);
            throw new AgendaException(AgendaException.AGENDA_NOT_FIND);
        }

        return agenda;
    }

}
