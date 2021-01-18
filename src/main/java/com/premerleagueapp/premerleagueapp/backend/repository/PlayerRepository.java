package com.premerleagueapp.premerleagueapp.backend.repository;

import com.premerleagueapp.premerleagueapp.backend.domain.Player;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
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

    @Query("select c from PLAYERS c " +
            "where lower(c.name) like lower(concat('%', :searchTerm, '%')) " +
            "or lower(c.surname) like lower(concat('%', :searchTerm, '%'))") //
    List<Player> search(@Param("searchTerm") String searchTerm); //
}
