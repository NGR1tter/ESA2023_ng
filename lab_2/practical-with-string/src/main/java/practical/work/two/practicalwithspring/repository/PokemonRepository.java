package practical.work.two.practicalwithspring.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import practical.work.two.practicalwithspring.model.Pokemon;

import java.util.Optional;

public interface PokemonRepository extends CrudRepository<Pokemon, Integer> {
    @EntityGraph(value = "with-coach")
    Optional<Pokemon> findById(Integer id);

    @SuppressWarnings("unchecked")
    Pokemon save(Pokemon pokemon);

    void deleteById(Integer id);
}
