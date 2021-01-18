package com.premerleagueapp.premerleagueapp.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PlayerDto {

    private Long id;
    private String name;
    private String surname;
    private String dateOfBirth;
    private Long positionId;
    private Long teamId;
}
