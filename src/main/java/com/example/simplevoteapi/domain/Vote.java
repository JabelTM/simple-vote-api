package com.example.simplevoteapi.domain;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class Vote {

    @NotNull
    private VoteEnum vote;

    @NotNull
    private User user;

}
