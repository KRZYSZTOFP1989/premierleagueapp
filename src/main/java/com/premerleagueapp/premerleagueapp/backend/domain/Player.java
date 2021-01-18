package com.premerleagueapp.premerleagueapp.backend.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.premerleagueapp.premerleagueapp.backend.domain.Position;
import com.premerleagueapp.premerleagueapp.backend.domain.Team;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "PLAYERS")
public class Player {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String surname;
    private String dateOfBirth;

    @NotNull
    @JsonBackReference(value = "player-team")
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "team_id")
    private Team team;

    @NotNull
    @JsonBackReference(value = "player-position")
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "position_id")
    private Position position;
}
