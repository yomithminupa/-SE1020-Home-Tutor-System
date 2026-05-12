package com.example.hometutor.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("ACADEMIC")
public class AcademicSubject extends Subject {
    private String stream;

    public AcademicSubject() {
    }

    public AcademicSubject(String id, String name, String gradeLevel, String description, String stream) {
        super(id, name, gradeLevel, description);
        this.stream = stream;
    }

    @Override
    public String getCategory() {
        return "ACADEMIC";
    }

    @Override
    public String displaySubject() {
        return getName() + " - " + stream + " stream";
    }

    public String getStream() {
        return stream;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }
}
