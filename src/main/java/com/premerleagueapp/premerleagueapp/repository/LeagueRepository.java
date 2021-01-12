package com.premerleagueapp.premerleagueapp.repository;

import com.premerleagueapp.premerleagueapp.domain.League;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface LeagueRepository extends CrudRepository<League, Long> {

    @Override
    List<League> findAll();

    @Override
    Optional<League> findById(Long id);

    @Override
    <S extends League> S save(S legaue);

    @Override
    void deleteById(Long id);
}