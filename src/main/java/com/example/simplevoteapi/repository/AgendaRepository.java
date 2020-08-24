package com.example.simplevoteapi.repository;

import com.example.simplevoteapi.domain.Agenda;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AgendaRepository extends CrudRepository<Agenda, Long> {

    Agenda findById(long id);

    List<Agenda> findAll();

}
