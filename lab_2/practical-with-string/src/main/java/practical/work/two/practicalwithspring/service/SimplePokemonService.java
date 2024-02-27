package practical.work.two.practicalwithspring.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import practical.work.two.practicalwithspring.dto.view.PokemonDTO;
import practical.work.two.practicalwithspring.model.Coach;
import practical.work.two.practicalwithspring.model.Pokemon;
import practical.work.two.practicalwithspring.repository.PokemonRepository;

import java.util.Optional;

@AllArgsConstructor
@Service
public class SimplePokemonService implements PokemonService {
    private final PokemonRepository repository;

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
