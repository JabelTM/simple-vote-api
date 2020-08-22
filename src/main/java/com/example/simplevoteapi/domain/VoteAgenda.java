package com.example.simplevoteapi.domain;

import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class VoteAgenda {

    private long id;
    private String description;
    private List<User> votes = new ArrayList<>();

}
