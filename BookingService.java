package com.example.hometutor.service;

import com.example.hometutor.model.Booking;
import com.example.hometutor.repository.BookingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
public class BookingService extends AbstractCrudService<Booking> {

    public BookingService(BookingRepository bookingRepository) {
        super(bookingRepository);
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

    private static boolean contains(String value, String query) {
        return value != null && value.toLowerCase(Locale.ROOT).contains(query);
    }
}
