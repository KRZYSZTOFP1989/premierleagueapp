package com.premerleagueapp.premerleagueapp.backend.service;

import com.premerleagueapp.premerleagueapp.backend.domain.Team;
import com.premerleagueapp.premerleagueapp.backend.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;

    @Override
    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    @Override
    public Team findById(final Long teamId) throws Exception {
        return teamRepository.findById(teamId).orElseThrow(()
                -> new Exception("Team not found"));
    }

    public List<Team> findAll(String stringFilter) {
        if (stringFilter == null || stringFilter.isEmpty()) {
            return teamRepository.findAll();
        } else {
            return teamRepository.search(stringFilter);
        }
    }

    @Override
    public Team saveTeam(final Team team) {
        return teamRepository.save(team);
    }

    @Override
    public Team updateTeam(final Team team) throws Exception {
        Long id = team.getId();
        Optional<Team> idNumber = teamRepository.findById(id);

        if (!idNumber.isPresent()) {
            throw new Exception("The league does not exist!");
        }
        return teamRepository.save(team);
    }

    @Override
    public void deleteTeam(final Long teamId) {
        teamRepository.deleteById(teamId);
    }
}
