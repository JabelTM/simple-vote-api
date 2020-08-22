package com.example.simplevoteapi.repository;

import com.example.simplevoteapi.domain.VoteAgenda;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class VoteAgendaRepository {

    private Map<Long, VoteAgenda> repository = new HashMap<Long, VoteAgenda>();

    public VoteAgenda save(VoteAgenda voteAgenda) {
        if (repository.containsKey(voteAgenda.getId())) {
            repository.replace(voteAgenda.getId(), voteAgenda);
        } else {
            Long newId = Long.valueOf(repository.size() + 1);
            voteAgenda.setId(newId);
            repository.put(newId, voteAgenda);
        }

        return voteAgenda;
    }

    public List<VoteAgenda> findAll() {
        return new ArrayList<>(repository.values());
    }

    public VoteAgenda findById(long id) {
        return repository.get(id);
    }
}
