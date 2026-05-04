package com.example.hometutor.service;

import com.example.hometutor.model.PublicReview;
import com.example.hometutor.model.Review;
import com.example.hometutor.model.VerifiedReview;
import com.example.hometutor.repository.FileRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.file.Paths;
import java.util.List;
import java.util.Locale;

@Service
public class ReviewService extends AbstractCrudService<Review> {

    public ReviewService(@Value("${app.data-dir:data}") String dataDir) {
        super(new FileRepository<>(Paths.get(dataDir).resolve("reviews.txt"), ReviewService::fromFileLine));
    }

    public Review buildReview(String type, String id, String tutorId, String userId, int rating,
                              String comment, String nickname, String bookingId) {
        if ("VERIFIED".equalsIgnoreCase(type)) {
            return new VerifiedReview(id, tutorId, userId, rating, comment, bookingId);
        }
        return new PublicReview(id, tutorId, userId, rating, comment, nickname);
    }

    @Override
    public List<Review> search(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return findAll();
        }
        String query = keyword.toLowerCase(Locale.ROOT);
        return findAll().stream()
                .filter(review -> contains(review.getId(), query)
                        || contains(review.getTutorId(), query)
                        || contains(review.getUserId(), query)
                        || contains(review.getComment(), query)
                        || contains(review.getReviewType(), query))
                .toList();
    }

    private static Review fromFileLine(String line) {
        String[] parts = line.split("\\|", -1);
        String type = value(parts, 0);
        String id = value(parts, 1);
        String tutorId = value(parts, 2);
        String userId = value(parts, 3);
        int rating = parseInt(value(parts, 4));
        String comment = value(parts, 5);
        if ("VERIFIED".equalsIgnoreCase(type)) {
            return new VerifiedReview(id, tutorId, userId, rating, comment, value(parts, 6));
        }
        return new PublicReview(id, tutorId, userId, rating, comment, value(parts, 6));
    }

    private static boolean contains(String value, String query) {
        return value != null && value.toLowerCase(Locale.ROOT).contains(query);
    }

    private static int parseInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private static String value(String[] parts, int index) {
        return index < parts.length ? parts[index] : "";
    }
}
