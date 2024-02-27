package practical.work.two.practicalwithspring.service;

import practical.work.two.practicalwithspring.dto.view.PokemonDTO;
import practical.work.two.practicalwithspring.model.Pokemon;

import java.util.Optional;

public interface PokemonService {
    Optional<Pokemon> findById(Integer id);

    void save(PokemonDTO dto);

    void modify(PokemonDTO dto);

    void deleteById(Integer id);
}
