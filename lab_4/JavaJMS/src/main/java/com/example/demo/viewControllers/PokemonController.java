package com.example.demo.viewControllers;

import com.example.demo.dto.view.PokemonDTO;
import com.example.demo.services.mvc.PokemonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pokemon")
public class PokemonController {
    private final PokemonService service;

    public PokemonController(PokemonService simplePokemonService) {
        service = simplePokemonService;
    }

    @GetMapping("/create/{id}")
    public String getCreatePage(@PathVariable String id, Model model) {
        model.addAttribute("coachId", id);
        return "/pokemon/create";
    }

    @PostMapping("/create")
    public String create(PokemonDTO dto) {
        service.save(dto);
        return "redirect:/coach/" + dto.getCoachId();
    }

    @GetMapping("/modify/{id}")
    public String getModifyPage(@PathVariable String id, Model model) {
        var pokemon = service.findById(Integer.parseInt(id));
        if (pokemon.isEmpty()) {
            model.addAttribute("message", "Не удалось найти покемона");
            return "error";
        }
        model.addAttribute("pokemon", pokemon.get());
        return "/pokemon/modify";
    }

    @PostMapping("/modify")
    public String modify(PokemonDTO dto) {
        service.modify(dto);
        return "redirect:/coach/" + dto.getId();
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        service.deleteById(Integer.parseInt(id));
        return "redirect:/coach/";
    }
}
