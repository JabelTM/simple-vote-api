package com.example.simplevoteapi.repository;

import com.example.simplevoteapi.domain.Session;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VoteSessionRepository extends CrudRepository<Session, Long> {

    Session findById(long id);

    List<Session> findAll();

}
