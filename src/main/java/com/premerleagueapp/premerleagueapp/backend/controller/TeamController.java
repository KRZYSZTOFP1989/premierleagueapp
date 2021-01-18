package com.premerleagueapp.premerleagueapp.backend.controller;

import com.premerleagueapp.premerleagueapp.backend.dto.TeamDto;
import com.premerleagueapp.premerleagueapp.backend.mapper.TeamMapper;
import com.premerleagueapp.premerleagueapp.backend.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("v1/")
@RequiredArgsConstructor
public class TeamController {

    private final TeamMapper teamMapper;
    private final TeamService teamService;

    @RequestMapping(method = RequestMethod.GET, value = "/team")
    public List<TeamDto> getTeams() {
        return teamMapper.MapToTeamDtoList(teamService.getAllTeams());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/team/{teamId}")
    public TeamDto getTeam(@PathVariable Long teamId) throws Exception {
        return teamMapper.MapToTeamDto(teamService.findById(teamId));
    }

    @RequestMapping(method = RequestMethod.POST, value = "team", consumes = APPLICATION_JSON_VALUE)
    public void createTeam(@RequestBody TeamDto teamDto) throws Exception {
        teamService.saveTeam(teamMapper.MapToTeam(teamDto));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "team")
    public TeamDto updateTeam(@RequestBody TeamDto teamDto) throws Exception {
        return teamMapper.MapToTeamDto(teamService.updateTeam(teamMapper.MapToTeam(teamDto)));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/team/{teamId}")
    public void deleteTeam(@PathVariable Long teamId) {
        teamService.deleteTeam(teamId);
    }
}
