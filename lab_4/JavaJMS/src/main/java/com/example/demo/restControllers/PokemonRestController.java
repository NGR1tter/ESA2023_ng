package com.example.demo.restControllers;

import com.example.demo.dto.rest.PokemonDTO;
import com.example.demo.models.Pokemon;
import com.example.demo.services.rest.PokemonRestService;
import com.example.demo.services.rest.SimplePokemonRestService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static com.example.demo.xsl.XSLTransform.getModelAndView;

@RestController
@RequestMapping(
        value = "/api/pokemon",
        produces = {"application/xml", "application/json"}
)
public class PokemonRestController {
    private final PokemonRestService service;

    public PokemonRestController(SimplePokemonRestService simplePokemonRestService) {
        service = simplePokemonRestService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Pokemon>> getAll() {
        var list = service.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pokemon> findById(@PathVariable Integer id) {
        var list = service.findById(id).get();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }


    @PostMapping("/create")
    public ResponseEntity<Void> create(@RequestBody PokemonDTO dto) {
        if (!service.save(dto)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/update")
    public ResponseEntity<Void> update(@RequestBody PokemonDTO dto) {
        if (!service.modify(dto)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete(@RequestBody PokemonDTO id) {
        if (!service.deleteById(id.getId())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping(path = "/xsl")
    public ModelAndView getStudent() throws JsonProcessingException {
        Iterable<Pokemon> list = service.findAll();
        return getModelAndView(list, "pokemonXSL");
    }
}
