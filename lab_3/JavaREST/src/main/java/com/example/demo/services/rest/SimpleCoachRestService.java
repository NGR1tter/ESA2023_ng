package com.example.demo.services.rest;

import com.example.demo.dto.rest.CoachAllDTO;
import com.example.demo.dto.rest.CoachDTO;
import com.example.demo.dto.rest.CoachModifyDTO;
import com.example.demo.models.Coach;
import com.example.demo.models.Pokemon;
import com.example.demo.repositories.CoachRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class SimpleCoachRestService implements CoachRestService {
    private final CoachRepository repository;

    @Override
    public List<CoachAllDTO> findAll() {
        return repository.findAll().stream()
                .map(coach -> {
                    CoachAllDTO dto = new CoachAllDTO();
                    dto.setId(coach.getId().toString());
                    dto.setName(coach.getName());
                    dto.setCity(coach.getCity());
                    return dto;
                }).toList();
    }

    @Override
    public Optional<CoachDTO> findById(Integer id) {
        return repository.findById(id).map(coach -> {
            CoachDTO dto = new CoachDTO();
            dto.setId(coach.getId().toString());
            dto.setName(coach.getName());
            dto.setCity(coach.getCity());
            dto.setPokemons(
                    coach.getPokemons().stream().map(p -> {
                                CoachDTO.Pokemon dtoPok = new CoachDTO.Pokemon();
                                dtoPok.setId(p.getId());
                                dtoPok.setName(p.getName());
                                dtoPok.setLife(p.getLife().toString());
                                dtoPok.setAttack(p.getAttack().toString());
                                return dtoPok;
                            })
                            .toList());
            return dto;
        });
    }

    @Override
    public boolean save(CoachDTO dto) {
        Coach coach = new Coach();
        coach.setName(dto.getName());
        coach.setCity(dto.getCity());
        coach.setPokemons(dto.getPokemons().stream()
                .map(dtoPok -> {
                    Pokemon pokemon = new Pokemon();
                    pokemon.setName(dtoPok.getName());
                    pokemon.setLife(Integer.valueOf(dtoPok.getLife()));
                    pokemon.setAttack(Integer.valueOf(dtoPok.getAttack()));
                    pokemon.setCoach(coach);
                    return pokemon;
                }).toList());
        var res = repository.save(coach);
        return res != null;
    }

    @Transactional
    @Override
    public boolean modify(CoachModifyDTO dto) {
        var coach = repository.findById(Integer.parseInt(dto.getId()));
        if (coach.isPresent()) {
            coach.get().setCity(dto.getCity());
            coach.get().setName(dto.getName());
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public boolean deleteById(Integer id) {
        repository.deleteById(id);
        var res = repository.findById(id);
        return res.isEmpty();
    }
}
