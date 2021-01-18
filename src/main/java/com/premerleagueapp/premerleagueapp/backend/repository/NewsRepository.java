package com.premerleagueapp.premerleagueapp.backend.repository;

import com.premerleagueapp.premerleagueapp.backend.domain.News;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface NewsRepository extends CrudRepository<News, Long> {

    @Override
    List<News> findAll();

    @Override
    Optional<News> findById(Long newsId);

    @Override
    <S extends News> S save(S news);

    @Override
    void deleteById(Long newsId);

    @Query("select c from NEWS c " +
            "where lower(c.title) like lower(concat('%', :searchTerm, '%')) " +
            "or lower(c.content) like lower(concat('%', :searchTerm, '%'))") //
    List<News> search(@Param("searchTerm") String searchTerm); //
}

