package com.example.hometutor.controller;

import com.example.hometutor.model.Booking;
import com.example.hometutor.service.BookingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/bookings")
public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping
    public String list(@RequestParam(required = false) String keyword, Model model) {
        model.addAttribute("bookings", bookingService.search(keyword));
        model.addAttribute("keyword", keyword == null ? "" : keyword);
        return "bookings";
    }

    @GetMapping("/new")
    public String newForm() {
        return "booking-form";
    }

    @PostMapping
    public String create(@RequestParam String id, @RequestParam String userId, @RequestParam String tutorId,
                         @RequestParam String subject, @RequestParam String bookingDate,
                         @RequestParam String bookingTime, @RequestParam String location,
                         @RequestParam String status, @RequestParam String sessionType) {
        Booking booking = bookingService.buildBooking(id, userId, tutorId, subject, bookingDate, bookingTime, location, status, sessionType);
        bookingService.create(booking);
        return "redirect:/bookings";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable String id, Model model) {
        model.addAttribute("booking", bookingService.findById(id).orElse(null));
        return "booking-form";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable String id, @RequestParam String userId, @RequestParam String tutorId,
                         @RequestParam String subject, @RequestParam String bookingDate,
                         @RequestParam String bookingTime, @RequestParam String location,
                         @RequestParam String status, @RequestParam String sessionType) {
        Booking booking = bookingService.buildBooking(id, userId, tutorId, subject, bookingDate, bookingTime, location, status, sessionType);
        bookingService.update(booking);
        return "redirect:/bookings";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        bookingService.delete(id);
        return "redirect:/bookings";
    }
}
