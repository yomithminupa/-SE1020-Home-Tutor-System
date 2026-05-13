package com.example.hometutor.config;

import com.example.hometutor.model.AcademicSubject;
import com.example.hometutor.model.AdminUser;
import com.example.hometutor.model.Booking;
import com.example.hometutor.model.CardPayment;
import com.example.hometutor.model.CashPayment;
import com.example.hometutor.model.HomeVisitTutor;
import com.example.hometutor.model.OnlineTutor;
import com.example.hometutor.model.PublicReview;
import com.example.hometutor.model.SkillSubject;
import com.example.hometutor.model.StudentUser;
import com.example.hometutor.model.ParentUser;
import com.example.hometutor.model.VerifiedReview;
import com.example.hometutor.repository.BookingRepository;
import com.example.hometutor.repository.PaymentRepository;
import com.example.hometutor.repository.ReviewRepository;
import com.example.hometutor.repository.SubjectRepository;
import com.example.hometutor.repository.TutorRepository;
import com.example.hometutor.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    private final UserRepository userRepository;
    private final TutorRepository tutorRepository;
    private final SubjectRepository subjectRepository;
    private final BookingRepository bookingRepository;
    private final PaymentRepository paymentRepository;
    private final ReviewRepository reviewRepository;

    public DataInitializer(UserRepository userRepository, TutorRepository tutorRepository,
                           SubjectRepository subjectRepository, BookingRepository bookingRepository,
                           PaymentRepository paymentRepository, ReviewRepository reviewRepository) {
        this.userRepository = userRepository;
        this.tutorRepository = tutorRepository;
        this.subjectRepository = subjectRepository;
        this.bookingRepository = bookingRepository;
        this.paymentRepository = paymentRepository;
        this.reviewRepository = reviewRepository;
    }

    @Override
    public void run(String... args) {
        seedUsers();
        seedTutors();
        seedSubjects();
        seedBookings();
        seedPayments();
        seedReviews();
    }

    private void seedUsers() {
        if (userRepository.count() > 0) {
            return;
        }
        userRepository.save(new StudentUser("U001", "Nimal Perera", "nimal@example.com", "0712345678", "pass123", "Grade 10", "Colombo"));
        userRepository.save(new ParentUser("U002", "Kasuni Fernando", "kasuni@example.com", "0774567891", "parent123", "Amaya Fernando", "Kandy"));
        userRepository.save(new AdminUser("A001", "Admin User", "admin@example.com", "0701112222", "admin123", "FULL"));
    }

    private void seedTutors() {
        if (tutorRepository.count() > 0) {
            return;
        }
        tutorRepository.save(new OnlineTutor("T001", "Ayesha Silva", "ayesha@example.com", "0761234567", "Mathematics", "BSc Mathematics", "Colombo", 1500.0, "Available", "Zoom"));
        tutorRepository.save(new HomeVisitTutor("T002", "Ruwan Jayasinghe", "ruwan@example.com", "0759876543", "Science", "BEd Science", "Kandy", 1800.0, "Weekends", 500.0));
        tutorRepository.save(new OnlineTutor("T003", "Tharindu Wijesinghe", "tharindu@example.com", "0743332211", "ICT", "BSc IT", "Galle", 1600.0, "Evenings", "Google Meet"));
    }

    private void seedSubjects() {
        if (subjectRepository.count() > 0) {
            return;
        }
        subjectRepository.save(new AcademicSubject("S001", "Mathematics", "Grade 10", "Local syllabus mathematics classes", "Science"));
        subjectRepository.save(new AcademicSubject("S002", "Science", "Grade 11", "Physics, chemistry, and biology support", "Science"));
        subjectRepository.save(new SkillSubject("S003", "Spoken English", "All Levels", "Communication and pronunciation practice", "Beginner"));
        subjectRepository.save(new AcademicSubject("S004", "ICT", "Grade 12", "Programming, database, and networking basics", "Technology"));
    }

    private void seedBookings() {
        if (bookingRepository.count() > 0) {
            return;
        }
        bookingRepository.save(new Booking("B001", "U001", "T001", "Mathematics", "2026-05-10", "16:00", "Online", "PENDING", "ONLINE"));
        bookingRepository.save(new Booking("B002", "U002", "T002", "Science", "2026-05-12", "09:30", "Kandy", "APPROVED", "HOME_VISIT"));
    }

    private void seedReviews() {
        if (reviewRepository.count() > 0) {
            return;
        }
        reviewRepository.save(new VerifiedReview("R001", "T001", "U001", 5, "Clear explanations and useful examples", "B001"));
        reviewRepository.save(new PublicReview("R002", "T002", "U002", 4, "Good home visit tutor for science", "Kasuni"));
    }

    private void seedPayments() {
        if (paymentRepository.count() > 0) {
            return;
        }
        paymentRepository.save(new CardPayment("P001", "B001", "U001", 3000.0, "2026-05-09", "PAID", "Advance payment", "Nimal Perera", "1234"));
        paymentRepository.save(new CashPayment("P002", "B002", "U002", 4100.0, "2026-05-10", "PENDING", "Pay after first session", "RCPT-1002"));
    }
}
