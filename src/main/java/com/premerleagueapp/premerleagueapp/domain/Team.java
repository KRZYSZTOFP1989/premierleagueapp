package com.premerleagueapp.premerleagueapp.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

