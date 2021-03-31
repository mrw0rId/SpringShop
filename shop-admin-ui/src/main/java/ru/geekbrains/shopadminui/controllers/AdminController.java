package ru.geekbrains.shopadminui.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.geekbrains.persist.repo.UserRepository;
import ru.geekbrains.persist.service.UserService;

@Controller
@RequestMapping("/")
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController (UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String index(){
        return "index";
    }

    @GetMapping("/users")
    public String users() {
        return "users";
    }

    @GetMapping("/roles")
    public String roles() {
        return "roles";
    }

    @GetMapping("/products")
    public String products() {
        return "products";
    }

    @GetMapping("/categories")
    public String categories() {
        return "categories";
    }

}
