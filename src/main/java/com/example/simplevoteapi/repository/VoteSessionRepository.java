package com.example.simplevoteapi.repository;

import com.example.simplevoteapi.domain.VoteSession;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class VoteSessionRepository {

    Map<Long, VoteSession> repository = new HashMap<>();


    public VoteSession save(VoteSession session) {
        if (repository.containsKey(session.getId())) {
            repository.replace(session.getId(), session);
        } else {
            Long newId = Long.valueOf(repository.size() + 1);
            session.setId(newId);
            repository.put(newId, session);
        }

        return session;
    }

    public List<VoteSession> findAll() {
        return new ArrayList<>(repository.values());
    }
}
