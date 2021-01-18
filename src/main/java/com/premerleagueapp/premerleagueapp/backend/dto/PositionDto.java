package com.premerleagueapp.premerleagueapp.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PositionDto {

    private Long id;
    private String name;
    private List<PlayerDto> players = new ArrayList<>();
}
