package com.example.hometutor.model;

public class AcademicSubject extends Subject {
    private String stream;

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

    @Override
    public String toFileString() {
        return String.join("|", getCategory(), commonFileFields(), clean(stream));
    }

    public String getStream() {
        return stream;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }
}
