package com.premerleagueapp.premerleagueapp.repository;

import com.premerleagueapp.premerleagueapp.domain.Player;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface PlayerRepository extends CrudRepository<Player, Long> {

    @Override
    List<Player> findAll();

    @Override
    Optional<Player> findById(Long id);

    @Override
    <S extends Player> S save(S player);

    @Override
    void deleteById(Long id);
}
