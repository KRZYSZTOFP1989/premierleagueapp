package com.premerleagueapp.premerleagueapp.backend.footballdataapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CompetitionDto {

    private final int id;
    private final String name;
    private final String code;
    private final String plan;
    private final int numberOfAvailableSeasons;
    private final LocalDateTime lastUpdated;
}
