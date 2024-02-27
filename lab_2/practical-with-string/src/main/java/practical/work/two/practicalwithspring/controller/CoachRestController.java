package practical.work.two.practicalwithspring.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import practical.work.two.practicalwithspring.dto.rest.CoachAllDTO;
import practical.work.two.practicalwithspring.dto.rest.CoachDTO;
import practical.work.two.practicalwithspring.dto.rest.CoachModifyDTO;
import practical.work.two.practicalwithspring.service.CoachRestService;

import java.util.List;

@RestController()
public class CoachRestController {
    private final CoachRestService service;

    public CoachRestController(CoachRestService simpleCoachRestService) {
        service = simpleCoachRestService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<CoachAllDTO>> getAll() {
        var list = service.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<CoachDTO> getById(@PathVariable Integer id) {
        var coach = service.findById(id);
        return coach.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    /**
     * Можно было бы отправлять в ответ id созданного тренера
     */
    @PostMapping("/create")
    public ResponseEntity<Void> create(@RequestBody CoachDTO dto) {
        if (!service.save(dto)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/update")
    public ResponseEntity<Void> update(@RequestBody CoachModifyDTO dto) {
        if (!service.modify(dto)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/delete")
    public ResponseEntity<Void> delete(@RequestBody CoachAllDTO id) {
        if (!service.deleteById(Integer.parseInt(id.getId()))) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
