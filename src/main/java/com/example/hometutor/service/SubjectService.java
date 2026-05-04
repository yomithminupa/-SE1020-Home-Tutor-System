package com.example.hometutor.service;

import com.example.hometutor.model.AcademicSubject;
import com.example.hometutor.model.SkillSubject;
import com.example.hometutor.model.Subject;
import com.example.hometutor.repository.FileRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.file.Paths;
import java.util.List;
import java.util.Locale;

@Service
public class SubjectService extends AbstractCrudService<Subject> {

    public SubjectService(@Value("${app.data-dir:data}") String dataDir) {
        super(new FileRepository<>(Paths.get(dataDir).resolve("subjects.txt"), SubjectService::fromFileLine));
    }

    public Subject buildSubject(String category, String id, String name, String gradeLevel,
                                String description, String stream, String skillLevel) {
        if ("SKILL".equalsIgnoreCase(category)) {
            return new SkillSubject(id, name, gradeLevel, description, skillLevel);
        }
        return new AcademicSubject(id, name, gradeLevel, description, stream);
    }

    @Override
    public List<Subject> search(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return findAll();
        }
        String query = keyword.toLowerCase(Locale.ROOT);
        return findAll().stream()
                .filter(subject -> contains(subject.getId(), query)
                        || contains(subject.getName(), query)
                        || contains(subject.getGradeLevel(), query)
                        || contains(subject.getCategory(), query))
                .toList();
    }

    private static Subject fromFileLine(String line) {
        String[] parts = line.split("\\|", -1);
        String category = value(parts, 0);
        String id = value(parts, 1);
        String name = value(parts, 2);
        String gradeLevel = value(parts, 3);
        String description = value(parts, 4);
        if ("SKILL".equalsIgnoreCase(category)) {
            return new SkillSubject(id, name, gradeLevel, description, value(parts, 5));
        }
        return new AcademicSubject(id, name, gradeLevel, description, value(parts, 5));
    }

    private static boolean contains(String value, String query) {
        return value != null && value.toLowerCase(Locale.ROOT).contains(query);
    }

    private static String value(String[] parts, int index) {
        return index < parts.length ? parts[index] : "";
    }
}
