package com.premerleagueapp.premerleagueapp.backend.service;

import com.premerleagueapp.premerleagueapp.backend.domain.Player;

import java.util.List;

public interface PlayerService {

    List<Player> getAllPlayers();
    Player getPlayerById(final Long playerId) throws Exception;
    void deletePlayer(final Long playerId);
    Player savePlayer(final Player player);
    Player updatePlayer(final Player player) throws Exception;
    List<Player> findAll(String stringFilter);
}
