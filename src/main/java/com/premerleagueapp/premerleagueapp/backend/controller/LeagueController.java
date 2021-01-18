package com.premerleagueapp.premerleagueapp.backend.controller;

import com.premerleagueapp.premerleagueapp.backend.dto.LeagueDto;
import com.premerleagueapp.premerleagueapp.backend.mapper.LeagueMapper;
import com.premerleagueapp.premerleagueapp.backend.service.LeagueService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("v1/")
@RequiredArgsConstructor
public class LeagueController {

    private final LeagueMapper leagueMapper;
    private final LeagueService leagueService;

    @RequestMapping(method = RequestMethod.GET, value = "/league")
    public List<LeagueDto> getLeagues() {
        return leagueMapper.MapToLeagueDtoList(leagueService.getAllLeagues());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/league/{leagueId}")
    public LeagueDto getLeague(@PathVariable Long leagueId) throws Exception {
        return leagueMapper.MapToLeagueDto(leagueService.findById(leagueId));
    }

    @RequestMapping(method = RequestMethod.POST, value = "league", consumes = APPLICATION_JSON_VALUE)
    public void createLeague(@RequestBody LeagueDto leagueDto) {
        leagueService.saveLeague(leagueMapper.MapToLeague(leagueDto));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "league")
    public LeagueDto updateLeague(@RequestBody LeagueDto leagueDto) throws Exception {
        return leagueMapper.MapToLeagueDto(leagueService.updateLeague(leagueMapper.MapToLeague(leagueDto)));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/league/{leagueId}")
    public void deleteLeague(@PathVariable Long leagueId) {
        leagueService.deleteLeague(leagueId);
    }
}

