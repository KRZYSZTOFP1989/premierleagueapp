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

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "POSITIONS")
public class Position {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @JsonManagedReference(value = "player-position")
    @OneToMany(targetEntity = Player.class,
            mappedBy = "position",
            cascade = CascadeType.REMOVE,
            fetch = FetchType.EAGER)
    private List<Player> players = new ArrayList<>();
}
