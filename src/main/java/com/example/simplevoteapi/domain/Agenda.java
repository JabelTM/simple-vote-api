package com.example.simplevoteapi.domain;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name="agendas")
@EqualsAndHashCode(of = "id")
@SequenceGenerator(name = "SEQ_A", sequenceName = "SEQ_AGENDA", allocationSize = 1)
@Getter @Setter @NoArgsConstructor
public class Agenda {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_A")
    private long id;

    private String description;

}