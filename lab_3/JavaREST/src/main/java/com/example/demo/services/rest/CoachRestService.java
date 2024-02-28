package com.example.demo.services.rest;


import com.example.demo.dto.rest.CoachAllDTO;
import com.example.demo.dto.rest.CoachDTO;
import com.example.demo.dto.rest.CoachModifyDTO;

import java.util.List;
import java.util.Optional;

public interface CoachRestService {
    List<CoachAllDTO> findAll();

    Optional<CoachDTO> findById(Integer id);

    boolean save(CoachDTO dto);

    boolean modify(CoachModifyDTO dto);

    boolean deleteById(Integer id);
}
