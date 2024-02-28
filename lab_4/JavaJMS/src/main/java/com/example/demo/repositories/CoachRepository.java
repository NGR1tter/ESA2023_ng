package com.example.demo.repositories;

import com.example.demo.models.Coach;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CoachRepository extends CrudRepository<Coach, Integer> {
    List<Coach> findAll();

    Optional<Coach> findById(Integer id);

    @SuppressWarnings("unchecked")
    Coach save(Coach coach);

    void deleteById(Integer id);
}
