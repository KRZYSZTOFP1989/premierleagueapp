package com.premerleagueapp.premerleagueapp.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "PLAYERS")
public class Player {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String position;
    private String dateOfBirth;

    @NotNull
    @JsonBackReference(value = "player-team")
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "team_id")
    private Team team;
}
