package com.premerleagueapp.premerleagueapp.repository;

import com.premerleagueapp.premerleagueapp.domain.Team;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface TeamRepository extends CrudRepository<Team, Long> {

    @Override
    List<Team> findAll();

    @Override
    Optional<Team> findById(Long id);

    @Override
    <S extends Team> S save(S team);

    @Override
    void deleteById(Long id);
}
