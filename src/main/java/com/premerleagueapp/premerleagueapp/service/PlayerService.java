package com.premerleagueapp.premerleagueapp.service;

import com.premerleagueapp.premerleagueapp.domain.Player;

import java.util.List;

public interface PlayerService {

    List<Player> getAllPlayer();
    Player getPlayerById(final Long playerId) throws Exception;
    void deletePlayer(final Long playerId);
    Player savePlayer(final Player player);
    Player updatePlayer(final Player player) throws Exception;
}
