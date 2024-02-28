package com.example.demo.services.mvc;

import com.example.demo.dto.view.CoachDTO;
import com.example.demo.models.Coach;
import com.example.demo.repositories.CoachRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class SimpleCoachService implements CoachService {
    private CoachRepository repository;

    @Override
    public List<CoachDTO> findAll() {
        return repository.findAll().stream().map(this::convertToDto).toList();
    }

    @Override
    public Optional<CoachDTO> findById(Integer id) {
        return repository.findById(id).map(this::convertToDto);
    }

    private CoachDTO convertToDto(Coach coach) {
        CoachDTO dto = new CoachDTO();
        dto.setId(coach.getId().toString());
        dto.setCity(coach.getCity());
        dto.setName(coach.getName());
        if (!coach.getPokemons().isEmpty()) {
            dto.setPokemons(coach.getPokemons());
        }
        return dto;
    }

    @Override
    public boolean save(CoachDTO dto) {
        Coach coach = new Coach();
        coach.setCity(dto.getCity());
        coach.setName(dto.getName());
        Coach res = repository.save(coach);
        return res.getId() != null;
    }

    @Transactional
    @Override
    public boolean modify(CoachDTO dto) {
        var coach = repository.findById(Integer.parseInt(dto.getId()));
        if (coach.isPresent()) {
            coach.get().setName(dto.getName());
            coach.get().setCity(dto.getCity());
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
