package com.example.simplevoteapi.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {

    private long id;
    private String cpf;
    private Vote vote;
}
