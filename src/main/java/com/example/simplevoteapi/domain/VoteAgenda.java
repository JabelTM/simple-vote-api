package com.example.simplevoteapi.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class VoteAgenda {

    private long id;
    private String description;
    private List<Vote> votes = new ArrayList<>();

}
