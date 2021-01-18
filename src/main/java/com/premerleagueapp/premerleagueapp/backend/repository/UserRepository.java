package com.premerleagueapp.premerleagueapp.backend.repository;

import com.premerleagueapp.premerleagueapp.backend.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface UserRepository extends CrudRepository<User, Long> {

    @Override
    List<User> findAll();

    @Override
    Optional<User> findById(Long id);

    @Override
    <S extends User> S save(S user);

    @Override
    void deleteById(Long id);
}
