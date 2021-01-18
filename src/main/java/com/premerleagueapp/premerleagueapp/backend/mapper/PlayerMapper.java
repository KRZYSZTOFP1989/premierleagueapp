package com.premerleagueapp.premerleagueapp.backend.mapper;

import com.premerleagueapp.premerleagueapp.backend.domain.Player;
import com.premerleagueapp.premerleagueapp.backend.domain.Position;
import com.premerleagueapp.premerleagueapp.backend.domain.Team;
import com.premerleagueapp.premerleagueapp.backend.dto.PlayerDto;
import com.premerleagueapp.premerleagueapp.backend.repository.PositionRepository;
import com.premerleagueapp.premerleagueapp.backend.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class PlayerMapper {

    private final TeamRepository teamRepository;
    private final PositionRepository positionRepository;

    public Player MapToPlayer(final PlayerDto playerDto) throws Exception {
        Player player = new Player();
        player.setId(playerDto.getId());
        player.setName(playerDto.getName());
        player.setSurname(playerDto.getSurname());
        Position position = positionRepository.findById(playerDto.getPositionId()).orElseThrow(() -> new Exception("Team id " + playerDto.getPositionId() + " not found"));
        player.setPosition(position);
        player.setDateOfBirth(playerDto.getDateOfBirth());
        Team team = teamRepository.findById(playerDto.getTeamId()).orElseThrow(() -> new Exception("Team id " + playerDto.getTeamId() + " not found"));
        player.setTeam(team);
        return player;
    }

    public PlayerDto MapToPlayerDto(final Player player) {
        PlayerDto playerDto = new PlayerDto();
        playerDto.setId(player.getId());
        playerDto.setName(player.getName());
        playerDto.setSurname(player.getSurname());
        playerDto.setPositionId(player.getPosition().getId());
        playerDto.setDateOfBirth(player.getDateOfBirth());
        playerDto.setTeamId(player.getTeam().getId());
        return playerDto;
    }

    public List<PlayerDto> MapToPlayerDtoList(final List<Player> playerList) {
        return playerList.stream()
                .map(this::MapToPlayerDto)
                .collect(Collectors.toList());
    }
}
