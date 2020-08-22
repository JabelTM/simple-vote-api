package com.example.simplevoteapi.web;

import com.example.simplevoteapi.domain.request.CreateVoteSessionRequest;
import com.example.simplevoteapi.domain.response.VoteSessionResponse;
import com.example.simplevoteapi.services.VoteSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/session")
public class VoteSessionController {

    @Autowired
    private VoteSessionService voteSessionService;

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody @Valid CreateVoteSessionRequest request) {
        try {
            VoteSessionResponse response = voteSessionService.create(request);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<VoteSessionResponse>> getSessions() {
        return new ResponseEntity<>(voteSessionService.getSessions(), HttpStatus.OK);
    }

}
