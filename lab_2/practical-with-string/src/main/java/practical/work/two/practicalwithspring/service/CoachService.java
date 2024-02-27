package practical.work.two.practicalwithspring.service;

import practical.work.two.practicalwithspring.dto.view.CoachDTO;

import java.util.List;
import java.util.Optional;

public interface CoachService {
    List<CoachDTO> findAll();

    Optional<CoachDTO> findById(Integer id);

    boolean save(CoachDTO dto);

    boolean modify(CoachDTO dto);

    boolean deleteById(Integer id);
}
