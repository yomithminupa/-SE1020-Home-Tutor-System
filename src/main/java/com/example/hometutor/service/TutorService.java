package com.example.hometutor.service;

import com.example.hometutor.model.HomeVisitTutor;
import com.example.hometutor.model.OnlineTutor;
import com.example.hometutor.model.Tutor;
import com.example.hometutor.repository.FileRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.file.Paths;
import java.util.List;
import java.util.Locale;

@Service
public class TutorService extends AbstractCrudService<Tutor> {

    public TutorService(@Value("${app.data-dir:data}") String dataDir) {
        super(new FileRepository<>(Paths.get(dataDir).resolve("tutors.txt"), TutorService::fromFileLine));
    }

    public Tutor buildTutor(String type, String id, String name, String email, String phone, String subject,
                            String qualification, String location, double hourlyRate, String availability,
                            String platform, double travelFee) {
        if ("HOME_VISIT".equalsIgnoreCase(type)) {
            return new HomeVisitTutor(id, name, email, phone, subject, qualification, location, hourlyRate, availability, travelFee);
        }
        return new OnlineTutor(id, name, email, phone, subject, qualification, location, hourlyRate, availability, platform);
    }

    @Override
    public List<Tutor> search(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return findAll();
        }
        String query = keyword.toLowerCase(Locale.ROOT);
        return findAll().stream()
                .filter(tutor -> contains(tutor.getId(), query)
                        || contains(tutor.getName(), query)
                        || contains(tutor.getSubject(), query)
                        || contains(tutor.getLocation(), query)
                        || contains(tutor.getAvailability(), query)
                        || contains(tutor.getTutorType(), query))
                .toList();
    }

    private static Tutor fromFileLine(String line) {
        String[] parts = line.split("\\|", -1);
        String type = value(parts, 0);
        String id = value(parts, 1);
        String name = value(parts, 2);
        String email = value(parts, 3);
        String phone = value(parts, 4);
        String subject = value(parts, 5);
        String qualification = value(parts, 6);
        String location = value(parts, 7);
        double hourlyRate = parseDouble(value(parts, 8));
        String availability = value(parts, 9);
        if ("HOME_VISIT".equalsIgnoreCase(type)) {
            return new HomeVisitTutor(id, name, email, phone, subject, qualification, location, hourlyRate, availability, parseDouble(value(parts, 10)));
        }
        return new OnlineTutor(id, name, email, phone, subject, qualification, location, hourlyRate, availability, value(parts, 10));
    }

    private static boolean contains(String value, String query) {
        return value != null && value.toLowerCase(Locale.ROOT).contains(query);
    }

    private static double parseDouble(String value) {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private static String value(String[] parts, int index) {
        return index < parts.length ? parts[index] : "";
    }
}
