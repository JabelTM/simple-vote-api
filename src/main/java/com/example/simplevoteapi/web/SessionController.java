package com.example.simplevoteapi.web;

import com.example.simplevoteapi.domain.request.CreateSessionRequest;
import com.example.simplevoteapi.domain.response.SessionResponse;
import com.example.simplevoteapi.services.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/session")
public class SessionController {

    @Autowired
    private SessionService sessionService;

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody @Valid CreateSessionRequest request) {
        try {
            SessionResponse response = sessionService.createAndOpenSession(request);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<SessionResponse>> getSessions() {
        return new ResponseEntity<>(sessionService.getSessions(), HttpStatus.OK);
    }

}
