package com.example.simplevoteapi.web;

import com.example.simplevoteapi.domain.request.CreateVoteAgendaRequest;
import com.example.simplevoteapi.domain.response.VoteAgendaResponse;
import com.example.simplevoteapi.services.VoteAgendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/agenda")
public class VoteAgendaController {

    @Autowired
    private VoteAgendaService voteAgendaService;

    @PostMapping
    public ResponseEntity<VoteAgendaResponse> create(@RequestBody @Valid CreateVoteAgendaRequest request) {
        return new ResponseEntity<>(voteAgendaService.create(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<VoteAgendaResponse>> getVoteAgendas() {
        return new ResponseEntity<>(voteAgendaService.getVoteAgendas(), HttpStatus.OK);
    }

}
