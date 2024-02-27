package practical.work.two.practicalwithspring.service;

import practical.work.two.practicalwithspring.dto.rest.CoachDTO;
import practical.work.two.practicalwithspring.dto.rest.CoachAllDTO;
import practical.work.two.practicalwithspring.dto.rest.CoachModifyDTO;

import java.util.List;
import java.util.Optional;

public interface CoachRestService {
    List<CoachAllDTO> findAll();

    Optional<CoachDTO> findById(Integer id);

    boolean save(CoachDTO dto);

    boolean modify(CoachModifyDTO dto);

    boolean deleteById(Integer id);
}
