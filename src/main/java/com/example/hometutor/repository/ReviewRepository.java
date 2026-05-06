package com.example.hometutor.repository;

import com.example.hometutor.model.Review;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ReviewRepository {

    private static final String FILE_PATH = "data/reviews.txt";

    public List<Review> findAll() {
        List<Review> reviews = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 6) {
                    Review r = new Review(
                            parts[0], parts[1], parts[2],
                            Integer.parseInt(parts[3]), parts[4], parts[5]
                    );
                    reviews.add(r);
                }
            }
        } catch (IOException e) {
            System.out.println("No reviews found: " + e.getMessage());
        }
        return reviews;
    }

    public void save(Review review) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            bw.write(review.getId() + "|" +
                    review.getStudentName() + "|" +
                    review.getTutorName() + "|" +
                    review.getStarRating() + "|" +
                    review.getComment() + "|" +
                    review.getDate());
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error saving review: " + e.getMessage());
        }
    }

    public void deleteById(String id) {
        List<Review> reviews = findAll();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH, false))) {
            for (Review r : reviews) {
                if (!r.getId().equals(id)) {
                    bw.write(r.getId() + "|" +
                            r.getStudentName() + "|" +
                            r.getTutorName() + "|" +
                            r.getStarRating() + "|" +
                            r.getComment() + "|" +
                            r.getDate());
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Error deleting review: " + e.getMessage());
        }
    }

    public Review findById(String id) {
        return findAll().stream()
                .filter(r -> r.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}