package com.example.simplevoteapi.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name="votes")
@EqualsAndHashCode(of = "id")
@SequenceGenerator(name = "SEQ_V", sequenceName = "SEQ_VOTES", allocationSize = 1)
@Getter @Setter @Builder
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_V")
    private long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private VoteEnum vote;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "agendaId")
    private Agenda agenda;

}
