package models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true, exclude = "pokemons")
@Entity
@NoArgsConstructor
public class Coach extends BaseId<Integer> {
    private String name;
    private String city;

    @OneToMany(mappedBy = "coach", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pokemon> pokemons = new ArrayList<>();

    public void addPokemon(Pokemon pokemon) {
        pokemon.setCoach(this);
        pokemons.add(pokemon);
    }

    public void removePokemon(Pokemon pokemon) {
        pokemon.setCoach(null);
        pokemons.remove(pokemon);
    }
}
