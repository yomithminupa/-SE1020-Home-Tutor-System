package com.example.hometutor.controller;

import com.example.hometutor.model.Subject;
import com.example.hometutor.service.SubjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/subjects")
public class SubjectController {
    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping
    public String list(@RequestParam(required = false) String keyword, Model model) {
        model.addAttribute("subjects", subjectService.search(keyword));
        model.addAttribute("keyword", keyword == null ? "" : keyword);
        return "subjects";
    }

    @GetMapping("/new")
    public String newForm() {
        return "subject-form";
    }

    @PostMapping
    public String create(@RequestParam String category, @RequestParam String id, @RequestParam String name,
                         @RequestParam String gradeLevel, @RequestParam String description,
                         @RequestParam(required = false) String stream,
                         @RequestParam(required = false) String skillLevel) {
        Subject subject = subjectService.buildSubject(category, id, name, gradeLevel, description, stream, skillLevel);
        subjectService.create(subject);
        return "redirect:/subjects";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable String id, Model model) {
        model.addAttribute("subject", subjectService.findById(id).orElse(null));
        return "subject-form";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable String id, @RequestParam String category, @RequestParam String name,
                         @RequestParam String gradeLevel, @RequestParam String description,
                         @RequestParam(required = false) String stream,
                         @RequestParam(required = false) String skillLevel) {
        Subject subject = subjectService.buildSubject(category, id, name, gradeLevel, description, stream, skillLevel);
        subjectService.update(subject);
        return "redirect:/subjects";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        subjectService.delete(id);
        return "redirect:/subjects";
    }
}
