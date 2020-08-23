package com.example.simplevoteapi.web;

import com.example.simplevoteapi.domain.Vote;
import com.example.simplevoteapi.domain.request.VoteRequest;
import com.example.simplevoteapi.services.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/vote")
public class VoteController {

    @Autowired
    private VoteService voteService;

    @PostMapping
    public ResponseEntity<?> vote(@RequestBody @Valid VoteRequest vote) {
        try {
            voteService.vote(vote);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{agendaId}")
    public ResponseEntity<List<Vote>> getVotes(@PathVariable long agendaId) {
        return new ResponseEntity<>(voteService.getVotes(agendaId), HttpStatus.OK);
    }
}
