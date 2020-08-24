package com.example.simplevoteapi.web;

import com.example.simplevoteapi.domain.request.CreateAgendaRequest;
import com.example.simplevoteapi.domain.response.AgendaResponse;
import com.example.simplevoteapi.services.AgendaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/agenda")
public class AgendaController {

    @Autowired
    private AgendaService agendaService;

    @PostMapping
    public ResponseEntity<AgendaResponse> create(@RequestBody @Valid CreateAgendaRequest request) {
        log.info("[AgendaController.create] Requisição de criação de pauta votação recebida.");
        return new ResponseEntity<>(agendaService.create(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AgendaResponse>> getAgendas() {
        log.info("[AgendaController.getAgendas] Requisição de listagem de pautas votação recebida.");
        return new ResponseEntity<>(agendaService.getAgendas(), HttpStatus.OK);
    }

}
