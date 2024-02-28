package com.example.demo.services.rest;

import com.example.demo.dto.rest.PokemonDTO;
import com.example.demo.models.Pokemon;

import java.util.List;
import java.util.Optional;

public interface PokemonRestService {
    List<Pokemon> findAll();

    Optional<Pokemon> findById(Integer id);

    boolean save(PokemonDTO dto);

    boolean modify(PokemonDTO dto);

    boolean deleteById(Integer id);
}
