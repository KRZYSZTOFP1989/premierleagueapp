package com.premerleagueapp.premerleagueapp.backend.mapper;

import com.premerleagueapp.premerleagueapp.backend.domain.League;
import com.premerleagueapp.premerleagueapp.backend.dto.LeagueDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class LeagueMapper {

    private final TeamMapper teamMapper;

    public League MapToLeague(final LeagueDto leagueDto) {
        League league = new League();
        league.setId(leagueDto.getId());
        league.setName(leagueDto.getName());
        league.setDescription(leagueDto.getDescription());
        return league;
    }

    public LeagueDto MapToLeagueDto(final League league) {
        LeagueDto leagueDto = new LeagueDto();
        leagueDto.setId(league.getId());
        leagueDto.setName(league.getName());
        leagueDto.setDescription(league.getDescription());
        leagueDto.setTeams(teamMapper.MapToTeamDtoList(league.getTeams()));
        return leagueDto;
    }

    public List<LeagueDto> MapToLeagueDtoList(final List<League> leagueList) {
        return leagueList.stream()
                .map(this::MapToLeagueDto)
                .collect(Collectors.toList());
    }
}
