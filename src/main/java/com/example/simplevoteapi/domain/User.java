package com.example.simplevoteapi.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name="users")
@EqualsAndHashCode(of = "id")
@Getter @Setter @NoArgsConstructor
public class User {

    @Id
    private long id;

    @Column(unique = true)
    private String cpf;

}
