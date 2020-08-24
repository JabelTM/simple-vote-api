package com.example.simplevoteapi.web;

import com.example.simplevoteapi.domain.request.CreateSessionRequest;
import com.example.simplevoteapi.domain.response.SessionResponse;
import com.example.simplevoteapi.services.SessionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/session")
public class SessionController {

    @Autowired
    private SessionService sessionService;

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody @Valid CreateSessionRequest request) {
        log.info("[SessionController.create] Requisição de abertura de sessão de votação recebida.");
        try {
            SessionResponse response = sessionService.createAndOpenSession(request);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("[SessionController.create] Erro ao tentar abrir uma sessão de votação.", e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<SessionResponse>> getSessions() {
        log.info("[SessionController.getSessions] Requisição de listagem sessões de votação recebida.");
        return new ResponseEntity<>(sessionService.getSessions(), HttpStatus.OK);
    }

}
