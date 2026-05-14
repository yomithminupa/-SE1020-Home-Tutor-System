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

    @Autowired
    private ReviewService reviewService;

    @GetMapping
    public String getAllReviews(Model model) {
        model.addAttribute("reviews", reviewService.getAllReviews());
        return "reviews";
    }

    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("review", new Review());
        return "review-form";
    }

    @PostMapping("/add")
    public String addReview(@ModelAttribute Review review) {
        reviewService.addReview(review);
        return "redirect:/reviews";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        model.addAttribute("review", reviewService.getReviewById(id));
        return "review-form";
    }

    @PostMapping("/update")
    public String updateReview(@ModelAttribute Review review) {
        reviewService.updateReview(review);
        return "redirect:/reviews";
    }

    @GetMapping("/delete/{id}")
    public String deleteReview(@PathVariable String id) {
        reviewService.deleteReview(id);
        return "redirect:/reviews";
    }
}