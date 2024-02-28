package com.example.demo.services.rest;

import com.example.demo.dto.rest.PokemonDTO;
import com.example.demo.models.Coach;
import com.example.demo.models.Pokemon;
import com.example.demo.repositories.PokemonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class SimplePokemonRestService implements PokemonRestService {
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
    public boolean save(PokemonDTO dto) {
        Pokemon pokemon = new Pokemon();
        pokemon.setName(dto.getName());
        pokemon.setLife(Integer.parseInt(dto.getLife()));
        pokemon.setAttack(Integer.parseInt(dto.getAttack()));

        Coach coach = new Coach();
        coach.addPokemon(pokemon);
        pokemon.setCoach(coach);
        var pok = repository.save(pokemon);
        return pok != null;
    }

    @Override
    public boolean modify(PokemonDTO dto) {
        var pokemon = findById(dto.getId());
        if (pokemon.isPresent()) {
            pokemon.get().setName(dto.getName());
            pokemon.get().setLife(Integer.parseInt(dto.getLife()));
            pokemon.get().setAttack(Integer.parseInt(dto.getAttack()));
            repository.save(pokemon.get());
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteById(Integer id) {
        repository.deleteById(id);
        var res = repository.findById(id);
        return res.isEmpty();
    }
}
