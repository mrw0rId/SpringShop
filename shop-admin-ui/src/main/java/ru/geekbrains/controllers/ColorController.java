package ru.geekbrains.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.geekbrains.error.NotFoundException;
import ru.geekbrains.persist.model.Colors;
import ru.geekbrains.persist.repo.ColorRepository;

import javax.validation.Valid;

@Controller
public class ColorController {

    private static final Logger logger = LoggerFactory.getLogger(ColorController.class);

    private final ColorRepository colorRepository;

    @Autowired
    public ColorController(ColorRepository colorRepository) {
        this.colorRepository = colorRepository;
    }

    @GetMapping("/colors")
    public String adminColorsPage(Model model) {
        model.addAttribute("activePage", "Colors");
        model.addAttribute("colors", colorRepository.findAll());
        return "colors";
    }

    @GetMapping("/colors/create")
    public String adminColorsCreatePage(Model model) {
        model.addAttribute("create", true);
        model.addAttribute("activePage", "Colors");
        model.addAttribute("color", new Colors());
        return "color_form";
    }

    @GetMapping("/color/{id}/edit")
    public String adminEditColor(Model model, @PathVariable("id") Long id) {
        model.addAttribute("edit", true);
        model.addAttribute("activePage", "Colors");
        model.addAttribute("color", colorRepository.findById(id).orElseThrow(NotFoundException::new));
        return "color_form";
    }

    @PostMapping("/color")
    public String adminInsertColor(@Valid Colors color, Model model, BindingResult bindingResult) {
        model.addAttribute("activePage", "Colors");

        if (bindingResult.hasErrors()) {
            return "color_form";
        }

        colorRepository.save(color);
        return "redirect:/colors";
    }

    @DeleteMapping("/color/{id}/delete")
    public String adminDeleteColor(Model model, @PathVariable("id") Long id) {
        colorRepository.deleteById(id);
        return "redirect:/colors";
    }
}
