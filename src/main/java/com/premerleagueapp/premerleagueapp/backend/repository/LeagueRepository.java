package com.premerleagueapp.premerleagueapp.backend.repository;

import com.premerleagueapp.premerleagueapp.backend.domain.League;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
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

    @Query("select c from LEAGUES c " +
            "where lower(c.name) like lower(concat('%', :searchTerm, '%')) " +
            "or lower(c.description) like lower(concat('%', :searchTerm, '%'))") //
    List<League> search(@Param("searchTerm") String searchTerm); //

}