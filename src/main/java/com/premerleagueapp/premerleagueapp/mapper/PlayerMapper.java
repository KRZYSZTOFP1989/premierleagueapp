package com.premerleagueapp.premerleagueapp.mapper;

import com.premerleagueapp.premerleagueapp.domain.Player;
import com.premerleagueapp.premerleagueapp.domain.Team;
import com.premerleagueapp.premerleagueapp.dto.PlayerDto;
import com.premerleagueapp.premerleagueapp.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class PlayerMapper {

    private final TeamRepository teamRepository;

    public Player MapToPlayer(final PlayerDto playerDto) throws Exception {
        Player player = new Player();
        player.setId(playerDto.getId());
        player.setName(playerDto.getName());
        player.setSurname(playerDto.getSurname());
        player.setPosition(playerDto.getPosition());
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
        playerDto.setPosition(player.getPosition());
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
