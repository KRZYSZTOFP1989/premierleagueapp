package com.premerleagueapp.premerleagueapp.backend.service;

import com.premerleagueapp.premerleagueapp.backend.domain.League;
import com.premerleagueapp.premerleagueapp.backend.repository.LeagueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LeagueServiceImpl implements LeagueService {

    private final LeagueRepository leagueRepository;

    @Override
    public List<League> getAllLeagues() {
        return leagueRepository.findAll();
    }

    @Override
    public League findById(final Long leagueId) throws Exception {
        return leagueRepository.findById(leagueId).orElseThrow(()
                -> new Exception("League not found"));
    }

    public List<League> findAll(String stringFilter) {
        if (stringFilter == null || stringFilter.isEmpty()) {
            return leagueRepository.findAll();
        } else {
            return leagueRepository.search(stringFilter);
        }
    }

    @Override
    public League saveLeague(final League league)  {
        return leagueRepository.save(league);
    }

    @Override
    public League updateLeague(final League league) throws Exception {
        Long id = league.getId();
        Optional<League> idNumber = leagueRepository.findById(id);

        if (!idNumber.isPresent()) {
            throw new Exception("The league does not exist!");
        }
        return leagueRepository.save(league);
    }

    @Override
    public void deleteLeague(final Long leagueId) {
        leagueRepository.deleteById(leagueId);
    }
}
