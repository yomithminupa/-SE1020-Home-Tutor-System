package com.example.hometutor.controller;

import com.example.hometutor.model.Tutor;
import com.example.hometutor.service.TutorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/tutors")
public class TutorController {
    private final TutorService tutorService;

    public TutorController(TutorService tutorService) {
        this.tutorService = tutorService;
    }

    @GetMapping
    public String list(@RequestParam(required = false) String keyword, Model model) {
        model.addAttribute("tutors", tutorService.search(keyword));
        model.addAttribute("keyword", keyword == null ? "" : keyword);
        return "tutors";
    }

    @GetMapping("/new")
    public String newForm() {
        return "tutor-form";
    }

    @PostMapping
    public String create(@RequestParam String type, @RequestParam String id, @RequestParam String name,
                         @RequestParam String email, @RequestParam String phone, @RequestParam String subject,
                         @RequestParam String qualification, @RequestParam String location,
                         @RequestParam String hourlyRate, @RequestParam String availability,
                         @RequestParam(required = false) String platform,
                         @RequestParam(required = false) String travelFee) {
        Tutor tutor = tutorService.buildTutor(type, id, name, email, phone, subject, qualification,
                location, parseDouble(hourlyRate), availability, platform, parseDouble(travelFee));
        tutorService.create(tutor);
        return "redirect:/tutors";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable String id, Model model) {
        model.addAttribute("tutor", tutorService.findById(id).orElse(null));
        return "tutor-form";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable String id, @RequestParam String type, @RequestParam String name,
                         @RequestParam String email, @RequestParam String phone, @RequestParam String subject,
                         @RequestParam String qualification, @RequestParam String location,
                         @RequestParam String hourlyRate, @RequestParam String availability,
                         @RequestParam(required = false) String platform,
                         @RequestParam(required = false) String travelFee) {
        Tutor tutor = tutorService.buildTutor(type, id, name, email, phone, subject, qualification,
                location, parseDouble(hourlyRate), availability, platform, parseDouble(travelFee));
        tutorService.update(tutor);
        return "redirect:/tutors";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        tutorService.delete(id);
        return "redirect:/tutors";
    }

    private double parseDouble(String value) {
        try {
            return Double.parseDouble(value);
        } catch (Exception e) {
            return 0;
        }
    }
}
