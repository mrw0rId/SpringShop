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
import ru.geekbrains.controllers.repr.UserRepr;
import ru.geekbrains.error.NotFoundException;
import ru.geekbrains.persist.model.Role;
import ru.geekbrains.persist.repo.RoleRepository;

import javax.validation.Valid;

@Controller
public class RoleController {

    private static final Logger logger = LoggerFactory.getLogger(RoleController.class);

    private final RoleRepository roleRepository;

    @Autowired
    public RoleController(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @GetMapping("/roles")
    public String adminRolesPage(Model model) {
        model.addAttribute("activePage", "Roles");
        model.addAttribute("roles", roleRepository.findAll());
        return "roles";
    }

    @GetMapping("/roles/create")
    public String adminRolesCreatePage(Model model) {
        model.addAttribute("create", true);
        model.addAttribute("activePage", "Roles");
        model.addAttribute("role", new Role());
        return "role_form";
    }

    @GetMapping("/role/{id}/edit")
    public String adminEditRole(Model model, @PathVariable("id") Long id) {
        model.addAttribute("edit", true);
        model.addAttribute("activePage", "Roles");
        model.addAttribute("role", roleRepository.findById(id).orElseThrow(NotFoundException::new));
        return "role_form";
    }

    @PostMapping("/role")
    public String adminInsertRole(@Valid Role role, Model model, BindingResult bindingResult) {
        model.addAttribute("activePage", "Roles");

        if (bindingResult.hasErrors()) {
            return "role_form";
        }

        roleRepository.save(role);
        return "redirect:/roles";
    }

    @DeleteMapping("/role/{id}/delete")
    public String adminDeleteRole(Model model, @PathVariable("id") Long id) {
        roleRepository.deleteById(id);
        return "redirect:/roles";
    }
}
