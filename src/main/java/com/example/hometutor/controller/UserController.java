package com.example.hometutor.controller;

import com.example.hometutor.model.User;
import com.example.hometutor.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String list(@RequestParam(required = false) String keyword, Model model) {
        model.addAttribute("users", userService.search(keyword));
        model.addAttribute("keyword", keyword == null ? "" : keyword);
        return "users";
    }

    @GetMapping("/new")
    public String newForm() {
        return "user-form";
    }

    @PostMapping
    public String create(@RequestParam String type, @RequestParam String id, @RequestParam String name,
                         @RequestParam String email, @RequestParam String phone, @RequestParam String password,
                         @RequestParam(required = false) String grade, @RequestParam(required = false) String address,
                         @RequestParam(required = false) String childName,
                         @RequestParam(required = false) String permissionLevel) {
        User user = userService.buildUser(type, id, name, email, phone, password, grade, address, childName, permissionLevel);
        userService.create(user);
        return "redirect:/users";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable String id, Model model) {
        model.addAttribute("user", userService.findById(id).orElse(null));
        return "user-form";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable String id, @RequestParam String type, @RequestParam String name,
                         @RequestParam String email, @RequestParam String phone, @RequestParam String password,
                         @RequestParam(required = false) String grade, @RequestParam(required = false) String address,
                         @RequestParam(required = false) String childName,
                         @RequestParam(required = false) String permissionLevel) {
        User user = userService.buildUser(type, id, name, email, phone, password, grade, address, childName, permissionLevel);
        userService.update(user);
        return "redirect:/users";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        userService.delete(id);
        return "redirect:/users";
    }
}
