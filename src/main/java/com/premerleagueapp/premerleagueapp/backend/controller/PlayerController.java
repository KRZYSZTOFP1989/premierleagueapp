package com.premerleagueapp.premerleagueapp.backend.controller;

import com.premerleagueapp.premerleagueapp.backend.dto.PlayerDto;
import com.premerleagueapp.premerleagueapp.backend.mapper.PlayerMapper;
import com.premerleagueapp.premerleagueapp.backend.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("v1/")
public class PlayerController {

    private final PlayerMapper playerMapper;
    private final PlayerService playerService;

    @RequestMapping(method = RequestMethod.GET, value = "/player")
    public List<PlayerDto> getPlayers() {
        return playerMapper.MapToPlayerDtoList(playerService.getAllPlayers());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/player/{playerId}")
    public PlayerDto getPlayer(@PathVariable Long playerId) throws Exception {
        return playerMapper.MapToPlayerDto(playerService.getPlayerById(playerId));
    }

    @RequestMapping(method = RequestMethod.POST, value = "player", consumes = APPLICATION_JSON_VALUE)
    public void createPlayer(@RequestBody PlayerDto playerDto) throws Exception {
        playerService.savePlayer(playerMapper.MapToPlayer(playerDto));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "player")
    public PlayerDto updateLeague(@RequestBody PlayerDto playerDto) throws Exception {
        return playerMapper.MapToPlayerDto(playerService.updatePlayer(playerMapper.MapToPlayer(playerDto)));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/player/{playerId}")
    public void deletePlayer(@PathVariable Long playerId) {
        playerService.deletePlayer(playerId);
    }
}
