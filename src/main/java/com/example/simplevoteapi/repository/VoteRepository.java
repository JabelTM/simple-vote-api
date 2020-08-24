package com.example.simplevoteapi.repository;

import com.example.simplevoteapi.domain.Vote;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VoteRepository extends CrudRepository<Vote, Long> {

    List<Vote> findAllByAgendaId(long agendaId);

}
