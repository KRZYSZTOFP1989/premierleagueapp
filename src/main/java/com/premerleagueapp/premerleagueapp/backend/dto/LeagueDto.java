package com.premerleagueapp.premerleagueapp.backend.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class LeagueDto {

    private Long id;
    private String name;
    private String description;
    private List<TeamDto> teams = new ArrayList<>();
}
