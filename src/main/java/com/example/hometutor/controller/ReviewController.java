package com.example.hometutor.controller;

import com.example.hometutor.model.Review;
import com.example.hometutor.service.ReviewService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public String list(@RequestParam(required = false) String keyword, Model model) {
        model.addAttribute("reviews", reviewService.search(keyword));
        model.addAttribute("keyword", keyword == null ? "" : keyword);
        return "reviews";
    }

    @GetMapping("/new")
    public String newForm() {
        return "review-form";
    }

    @PostMapping
    public String create(@RequestParam String type, @RequestParam String id, @RequestParam String tutorId,
                         @RequestParam String userId, @RequestParam String rating, @RequestParam String comment,
                         @RequestParam(required = false) String nickname,
                         @RequestParam(required = false) String bookingId) {
        Review review = reviewService.buildReview(type, id, tutorId, userId, parseInt(rating), comment, nickname, bookingId);
        reviewService.create(review);
        return "redirect:/reviews";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable String id, Model model) {
        model.addAttribute("review", reviewService.findById(id).orElse(null));
        return "review-form";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable String id, @RequestParam String type, @RequestParam String tutorId,
                         @RequestParam String userId, @RequestParam String rating, @RequestParam String comment,
                         @RequestParam(required = false) String nickname,
                         @RequestParam(required = false) String bookingId) {
        Review review = reviewService.buildReview(type, id, tutorId, userId, parseInt(rating), comment, nickname, bookingId);
        reviewService.update(review);
        return "redirect:/reviews";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        reviewService.delete(id);
        return "redirect:/reviews";
    }

    private int parseInt(String value) {
        try {
            int number = Integer.parseInt(value);
            return Math.max(1, Math.min(5, number));
        } catch (Exception e) {
            return 1;
        }
    }
}
