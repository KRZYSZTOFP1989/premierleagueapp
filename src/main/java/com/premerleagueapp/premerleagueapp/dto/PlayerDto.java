package com.premerleagueapp.premerleagueapp.dto;

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
    private String position;
    private String dateOfBirth;
    private Long teamId;
}
