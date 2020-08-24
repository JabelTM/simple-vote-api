package com.example.simplevoteapi.domain.request;

import com.example.simplevoteapi.domain.VoteEnum;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class VoteRequest {

    @NotNull
    private long voteAgendaId;

    @NotNull
    private VoteEnum vote;

    @NotNull
    private long userId;

    @NotBlank
    private String cpf;

}
