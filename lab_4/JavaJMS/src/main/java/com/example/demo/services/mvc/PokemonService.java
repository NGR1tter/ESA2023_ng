package com.example.demo.services.mvc;


import com.example.demo.dto.view.PokemonDTO;
import com.example.demo.models.Pokemon;

import java.util.List;
import java.util.Optional;

public interface PokemonService {
    List<Pokemon> findAll();
    Optional<Pokemon> findById(Integer id);

    void save(PokemonDTO dto);

    void modify(PokemonDTO dto);

    void deleteById(Integer id);
}
