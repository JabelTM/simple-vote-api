package com.example.simplevoteapi.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name="sessions")
@EqualsAndHashCode(of = "id")
@SequenceGenerator(name = "SEQ_S", sequenceName = "SEQ_SESSION", allocationSize = 1)
@Getter @Setter @NoArgsConstructor
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_S")
    private long id;

    private int sessionTimeInMinutes;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "id")
    private Agenda agenda;

    private boolean sessionOpen;

    private LocalDateTime sessionStart;

    private LocalDateTime sessionEnd;

}
