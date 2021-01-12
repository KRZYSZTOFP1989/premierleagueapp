package com.premerleagueapp.premerleagueapp.mapper;

import com.premerleagueapp.premerleagueapp.domain.League;
import com.premerleagueapp.premerleagueapp.domain.Team;
import com.premerleagueapp.premerleagueapp.dto.TeamDto;
import com.premerleagueapp.premerleagueapp.repository.LeagueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class TeamMapper {

    private final PlayerMapper playerMapper;
    private final LeagueRepository leagueRepository;

    public Team MapToTeam(final TeamDto teamDto) throws Exception {
        Team team = new Team();
        team.setId(teamDto.getId());
        team.setName(teamDto.getName());
        team.setDescription(teamDto.getDescription());
        team.setFounded(teamDto.getFounded());
        team.setGround(teamDto.getGround());
        League league = leagueRepository.findById(teamDto.getLeagueId()).orElseThrow(() -> new Exception("League id " + teamDto.getLeagueId() + " not found"));
        team.setLeague(league);
        return team;
    }

    public TeamDto MapToTeamDto(final Team team) {
        TeamDto teamDto = new TeamDto();
        teamDto.setId(team.getId());
        teamDto.setName(team.getName());
        teamDto.setDescription(team.getDescription());
        teamDto.setFounded(team.getFounded());
        teamDto.setGround(team.getGround());
        teamDto.setLeagueId(team.getLeague().getId());
        teamDto.setPlayers(playerMapper.MapToPlayerDtoList(team.getPlayers()));
        return teamDto;
    }

    public List<TeamDto> MapToTeamDtoList(List<Team> teamList) {
        return teamList.stream()
                .map(this::MapToTeamDto)
                .collect(Collectors.toList());
    }
}
