package com.example.hometutor.service;

import com.example.hometutor.model.Booking;
import com.example.hometutor.repository.FileRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.file.Paths;
import java.util.List;
import java.util.Locale;

@Service
public class BookingService extends AbstractCrudService<Booking> {

    public BookingService(@Value("${app.data-dir:data}") String dataDir) {
        super(new FileRepository<>(Paths.get(dataDir).resolve("bookings.txt"), BookingService::fromFileLine));
    }

    public Booking buildBooking(String id, String userId, String tutorId, String subject, String bookingDate,
                                String bookingTime, String location, String status, String sessionType) {
        return new Booking(id, userId, tutorId, subject, bookingDate, bookingTime, location, status, sessionType);
    }

    @Override
    public List<Booking> search(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return findAll();
        }
        String query = keyword.toLowerCase(Locale.ROOT);
        return findAll().stream()
                .filter(booking -> contains(booking.getId(), query)
                        || contains(booking.getUserId(), query)
                        || contains(booking.getTutorId(), query)
                        || contains(booking.getSubject(), query)
                        || contains(booking.getStatus(), query)
                        || contains(booking.getSessionType(), query))
                .toList();
    }

    private static Booking fromFileLine(String line) {
        String[] parts = line.split("\\|", -1);
        return new Booking(
                value(parts, 0),
                value(parts, 1),
                value(parts, 2),
                value(parts, 3),
                value(parts, 4),
                value(parts, 5),
                value(parts, 6),
                value(parts, 7),
                value(parts, 8)
        );
    }

    private static boolean contains(String value, String query) {
        return value != null && value.toLowerCase(Locale.ROOT).contains(query);
    }

    private static String value(String[] parts, int index) {
        return index < parts.length ? parts[index] : "";
    }
}
