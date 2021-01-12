package com.premerleagueapp.premerleagueapp.repository;

import com.premerleagueapp.premerleagueapp.domain.News;
import org.springframework.data.repository.CrudRepository;
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
}
