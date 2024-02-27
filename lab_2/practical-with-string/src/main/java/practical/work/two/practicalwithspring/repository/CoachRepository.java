package practical.work.two.practicalwithspring.repository;

import org.springframework.data.repository.CrudRepository;
import practical.work.two.practicalwithspring.model.Coach;

import java.util.List;
import java.util.Optional;

public interface CoachRepository extends CrudRepository<Coach, Integer> {
    List<Coach> findAll();

    Optional<Coach> findById(Integer id);

    @SuppressWarnings("unchecked")
    Coach save(Coach coach);

    void deleteById(Integer id);
}
