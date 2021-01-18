package com.premerleagueapp.premerleagueapp.backend.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "LEAGUES")
public class League {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;

    @JsonManagedReference(value = "team-league")
    @OneToMany(targetEntity = Team.class,
            mappedBy = "league",
            cascade = CascadeType.REMOVE,
            fetch = FetchType.EAGER)
    private List<Team> teams = new ArrayList<>();
}
