package com.example.simplevoteapi.repository;

import com.example.simplevoteapi.domain.Vote;
import org.springframework.data.repository.CrudRepository;

public interface VoteRepository extends CrudRepository<Vote, Long> {
}
