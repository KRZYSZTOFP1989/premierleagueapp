package com.premerleagueapp.premerleagueapp.backend.service;

import com.premerleagueapp.premerleagueapp.backend.domain.Player;
import com.premerleagueapp.premerleagueapp.backend.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService{

    private final PlayerRepository playerRepository;

    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    public Player getPlayerById(final Long id) throws Exception {
        return playerRepository.findById(id).orElseThrow(()
                -> new Exception("Player not found"));
    }

    public List<Player> findAll(String stringFilter) {
        if (stringFilter == null || stringFilter.isEmpty()) {
            return playerRepository.findAll();
        } else {
            return playerRepository.search(stringFilter);
        }
    }

    public Player savePlayer(final Player player) {
        return playerRepository.save(player);
    }

    public Player updatePlayer(final Player player) throws Exception {
        Long id = player.getId();
        Optional<Player> idNumber = playerRepository.findById(id);

        if (!idNumber.isPresent()) {
            throw new Exception("The player does not exist!");
        }
        return playerRepository.save(player);
    }

    public void deletePlayer(final Long playerId) {
        playerRepository.deleteById(playerId);
    }
}
