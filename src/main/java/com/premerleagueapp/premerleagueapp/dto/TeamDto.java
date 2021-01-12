package com.premerleagueapp.premerleagueapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TeamDto {

    private Long id;
    private String name;
    private String description;
    private String founded;
    private String ground;
    private Long leagueId;
    private List<PlayerDto> players = new ArrayList<>();
}
