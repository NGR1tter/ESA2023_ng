package com.example.demo.dto.rest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Pokemon {
        private Integer id;
        private String name;
        private String attack;
        private String life;
    }
}
