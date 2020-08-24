package com.example.simplevoteapi.web;

import com.example.simplevoteapi.domain.request.CreateAgendaRequest;
import com.example.simplevoteapi.domain.response.AgendaResponse;
import com.example.simplevoteapi.services.AgendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/agenda")
public class AgendaController {

    @Autowired
    private AgendaService agendaService;

    @PostMapping
    public ResponseEntity<AgendaResponse> create(@RequestBody @Valid CreateAgendaRequest request) {
        return new ResponseEntity<>(agendaService.create(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AgendaResponse>> getVoteAgendas() {
        return new ResponseEntity<>(agendaService.getVoteAgendas(), HttpStatus.OK);
    }

}
