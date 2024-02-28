package com.example.demo.services.mvc;

import com.example.demo.dto.view.PokemonDTO;
import com.example.demo.models.Coach;
import com.example.demo.models.Pokemon;
import com.example.demo.repositories.PokemonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class SimplePokemonService implements PokemonService {
    private final PokemonRepository repository;

    @Override
    public List<Pokemon> findAll() {
        return (List<Pokemon>) repository.findAll();
    }

    @Override
    public Optional<Pokemon> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public void save(PokemonDTO dto) {
        Pokemon pokemon = new Pokemon();
        pokemon.setName(dto.getName());
        pokemon.setLife(Integer.parseInt(dto.getLife()));
        pokemon.setAttack(Integer.parseInt(dto.getAttack()));

        Coach coach = new Coach();
        coach.setId(Integer.parseInt(dto.getCoachId()));
        coach.addPokemon(pokemon);
        pokemon.setCoach(coach);
        repository.save(pokemon);
    }

    @Transactional
    @Override
    public void modify(PokemonDTO dto) {
        var pokemon = findById(dto.getId());
        if (pokemon.isPresent()) {
            pokemon.get().setName(dto.getName());
            pokemon.get().setLife(Integer.parseInt(dto.getLife()));
            pokemon.get().setAttack(Integer.parseInt(dto.getAttack()));
            repository.save(pokemon.get());
        }
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}
