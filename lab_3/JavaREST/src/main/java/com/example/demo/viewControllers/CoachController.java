package com.example.demo.viewControllers;

import com.example.demo.dto.view.CoachDTO;
import com.example.demo.services.mvc.CoachService;
import com.example.demo.services.mvc.SimpleCoachService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/coach")
public class CoachController {
    private final CoachService service;

    public CoachController(SimpleCoachService simpleCoachService) {
        service = simpleCoachService;
    }

    @GetMapping("/")
    public String getAllPage(Model model) {
        model.addAttribute("coaches", service.findAll());
        return "/coach/coaches";
    }

    @GetMapping("/{id}")
    public String getPostPage(@PathVariable String id, Model model) {
        var coachOptional = service.findById(Integer.parseInt(id));
        if (coachOptional.isEmpty()) {
            return error("Не удалось найти тренера", model);
        }
        model.addAttribute("coach", coachOptional.get());
        return "/coach/coach";
    }

    @GetMapping("/create")
    public String getCreatePage() {
        return "/coach/create";
    }

    @PostMapping("/create")
    public String create(CoachDTO dto, Model model) {
        if (!service.save(dto)) {
            return error("Не удалось добавить тренера", model);
        }
        return "redirect:/coach/";
    }

    @GetMapping("/modify/{id}")
    public String getModifyPage(@PathVariable String id, Model model) {
        var coach = service.findById(Integer.parseInt(id));
        if (coach.isEmpty()) {
            return error("Не удалось найти тренера", model);
        }
        model.addAttribute("coach", coach.get());
        return "/coach/modify";
    }

    @PostMapping("/modify")
    public String modify(CoachDTO dto, Model model) {
        if (!service.modify(dto)) {
            return error("Не удалось изменить тренера", model);
        }
        return "redirect:/coach/" + dto.getId();
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id, Model model) {
        if (!service.deleteById(Integer.parseInt(id))) {
            return error("Не удалось удалить тренера", model);
        }
        return "redirect:/coach/";
    }

    private String error(String message, Model model) {
        model.addAttribute("message", message);
        return "/error";
    }
}