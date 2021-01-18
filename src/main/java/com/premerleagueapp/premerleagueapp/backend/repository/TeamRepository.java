package com.premerleagueapp.premerleagueapp.backend.repository;

import com.premerleagueapp.premerleagueapp.backend.domain.Team;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
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

    @Query("select c from TEAMS c " +
            "where lower(c.name) like lower(concat('%', :searchTerm, '%')) " +
            "or lower(c.description) like lower(concat('%', :searchTerm, '%'))") //
    List<Team> search(@Param("searchTerm") String searchTerm); //
}
