package com.premerleagueapp.premerleagueapp.backend.repository;

import com.premerleagueapp.premerleagueapp.backend.domain.Position;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PositionRepository extends CrudRepository<Position, Long> {

    @Override
    List<Position> findAll();

    @Override
    Optional<Position> findById(Long id);

    @Override
    <S extends Position> S save(S position);

    @Override
    void deleteById(Long id);
}
