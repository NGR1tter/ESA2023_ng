package practical.work.two.practicalwithspring.dto.view;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import practical.work.two.practicalwithspring.model.Pokemon;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CoachDTO {
    private String id;
    private String name;
    private String city;
    private List<Pokemon> pokemons;
}
