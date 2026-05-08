package com.example.hometutor.service;

import com.example.hometutor.model.Review;
import com.example.hometutor.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public void addReview(Review review) {
        review.setId(UUID.randomUUID().toString());
        review.setDate(LocalDate.now().toString());
        reviewRepository.save(review);
    }

    public void deleteReview(String id) {
        reviewRepository.deleteById(id);
    }

    public Review getReviewById(String id) {
        return reviewRepository.findById(id);
    }

    public void updateReview(Review updatedReview) {
        deleteReview(updatedReview.getId());
        reviewRepository.save(updatedReview);
    }
}