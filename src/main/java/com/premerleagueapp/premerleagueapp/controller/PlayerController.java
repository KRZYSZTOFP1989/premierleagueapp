package com.premerleagueapp.premerleagueapp.controller;

import com.premerleagueapp.premerleagueapp.dto.PlayerDto;
import com.premerleagueapp.premerleagueapp.mapper.PlayerMapper;
import com.premerleagueapp.premerleagueapp.service.PlayerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("v1/")
public class PlayerController {

    @Autowired
    PlayerMapper playerMapper;
    @Autowired
    PlayerServiceImpl playerServiceImpl;

    @RequestMapping(method = RequestMethod.GET, value = "/player")
    public List<PlayerDto> getPlayers() {
        return playerMapper.MapToPlayerDtoList(playerServiceImpl.getAllPlayer());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/player/{playerId}")
    public PlayerDto getPlayer(@PathVariable Long playerId) throws Exception {
        return playerMapper.MapToPlayerDto(playerServiceImpl.getPlayerById(playerId));
    }

    @RequestMapping(method = RequestMethod.POST, value = "player", consumes = APPLICATION_JSON_VALUE)
    public void createPlayer(@RequestBody PlayerDto playerDto) throws Exception {
        playerServiceImpl.savePlayer(playerMapper.MapToPlayer(playerDto));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "player")
    public PlayerDto updateLeague(@RequestBody PlayerDto playerDto) throws Exception {
        return playerMapper.MapToPlayerDto(playerServiceImpl.updatePlayer(playerMapper.MapToPlayer(playerDto)));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/player/{playerId}")
    public void deletePlayer(@PathVariable Long playerId) {
        playerServiceImpl.deletePlayer(playerId);
    }
}
