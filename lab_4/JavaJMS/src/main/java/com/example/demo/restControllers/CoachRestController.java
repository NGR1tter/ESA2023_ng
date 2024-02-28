package com.example.demo.restControllers;

import com.example.demo.dto.rest.CoachAllDTO;
import com.example.demo.dto.rest.CoachDTO;
import com.example.demo.dto.rest.CoachModifyDTO;
import com.example.demo.models.Coach;
import com.example.demo.services.rest.CoachRestService;
import com.example.demo.services.rest.SimpleCoachRestService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static com.example.demo.xsl.XSLTransform.getModelAndView;

@RestController
@RequestMapping(
        value = "/api/coach",
        produces = {"application/xml", "application/json"}
)
public class CoachRestController {
    private final CoachRestService service;

    public CoachRestController(SimpleCoachRestService simpleCoachRestService) {
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
    @GetMapping(path = "/xsl")
    public ModelAndView getStudent() throws JsonProcessingException {
        Iterable<CoachAllDTO> list = service.findAll();
        return getModelAndView(list, "coachXSL");
    }
}
