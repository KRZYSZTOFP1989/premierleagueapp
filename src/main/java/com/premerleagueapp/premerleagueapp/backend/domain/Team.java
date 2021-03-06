package com.premerleagueapp.premerleagueapp.backend.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "TEAMS")
public class Team {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private String founded;
    private String ground;

    @JsonBackReference(value = "team-league")
    @NotNull
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "league_id")
    private League league;

    @JsonManagedReference(value = "player-team")
    @OneToMany(targetEntity = Player.class,
            mappedBy = "team",
            cascade = CascadeType.REMOVE,
            fetch = FetchType.EAGER)
    private List<Player> players = new ArrayList<>();
}

