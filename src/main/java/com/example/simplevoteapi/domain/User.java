package com.example.simplevoteapi.domain;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name="users")
@EqualsAndHashCode(of = "id")
@Getter @Setter @Builder
public class User {

    @Id
    private long id;

    @Column(unique = true)
    private String cpf;

}
