package practical.work.two.practicalwithspring.dto.rest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PokemonDTO {
    private Integer id;
    private String name;
    private String attack;
    private String life;
}
