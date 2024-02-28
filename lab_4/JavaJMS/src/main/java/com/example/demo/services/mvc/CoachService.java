package com.example.demo.services.mvc;


import com.example.demo.dto.view.CoachDTO;

import java.util.List;
import java.util.Optional;

public interface CoachService {
    List<CoachDTO> findAll();

    Optional<CoachDTO> findById(Integer id);

    boolean save(CoachDTO dto);

    boolean modify(CoachDTO dto);

    boolean deleteById(Integer id);
}
