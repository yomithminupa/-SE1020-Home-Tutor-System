package com.example.hometutor.controller;

import com.example.hometutor.service.BookingService;
import com.example.hometutor.service.PaymentService;
import com.example.hometutor.service.ReviewService;
import com.example.hometutor.service.SubjectService;
import com.example.hometutor.service.TutorService;
import com.example.hometutor.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {
    private final UserService userService;
    private final TutorService tutorService;
    private final SubjectService subjectService;
    private final BookingService bookingService;
    private final PaymentService paymentService;
    private final ReviewService reviewService;

    public DashboardController(UserService userService, TutorService tutorService, SubjectService subjectService,
                               BookingService bookingService, PaymentService paymentService,
                               ReviewService reviewService) {
        this.userService = userService;
        this.tutorService = tutorService;
        this.subjectService = subjectService;
        this.bookingService = bookingService;
        this.paymentService = paymentService;
        this.reviewService = reviewService;
    }

    @GetMapping("/")
    public String dashboard(Model model) {
        model.addAttribute("userCount", userService.findAll().size());
        model.addAttribute("tutorCount", tutorService.findAll().size());
        model.addAttribute("subjectCount", subjectService.findAll().size());
        model.addAttribute("bookingCount", bookingService.findAll().size());
        model.addAttribute("paymentCount", paymentService.findAll().size());
        model.addAttribute("reviewCount", reviewService.findAll().size());
        return "index";
    }
}
