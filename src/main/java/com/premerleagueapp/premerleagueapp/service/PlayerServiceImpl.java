package com.premerleagueapp.premerleagueapp.service;

import com.premerleagueapp.premerleagueapp.domain.Player;
import com.premerleagueapp.premerleagueapp.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService{

    private final PlayerRepository playerRepository;

    public List<Player> getAllPlayer() {
        return playerRepository.findAll();
    }

    public Player getPlayerById(final Long id) throws Exception {
        return playerRepository.findById(id).orElseThrow(()
                -> new Exception("Player not found"));
    }

    public Player savePlayer(final Player player) {
        return playerRepository.save(player);
    }

    public Player updatePlayer(final Player player) {
        return playerRepository.save(player);
    }

    public void deletePlayer(final Long playerId) {
        playerRepository.deleteById(playerId);
    }
}
