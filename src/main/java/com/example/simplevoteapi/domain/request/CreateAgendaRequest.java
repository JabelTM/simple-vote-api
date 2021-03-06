package com.example.simplevoteapi.domain.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CreateAgendaRequest {

    @NotBlank(message = "Description may not be blank")
    private String description;

}
