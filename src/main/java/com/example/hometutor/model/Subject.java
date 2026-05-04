package com.example.hometutor.model;

public abstract class Subject implements FileEntity {
    private String id;
    private String name;
    private String gradeLevel;
    private String description;

    protected Subject(String id, String name, String gradeLevel, String description) {
        this.id = id;
        this.name = name;
        this.gradeLevel = gradeLevel;
        this.description = description;
    }

    public abstract String getCategory();

    public String displaySubject() {
        return name + " for " + gradeLevel;
    }

    protected String clean(String value) {
        return value == null ? "" : value.replace("|", "/").replace("\n", " ").trim();
    }

    protected String commonFileFields() {
        return String.join("|", clean(id), clean(name), clean(gradeLevel), clean(description));
    }

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGradeLevel() {
        return gradeLevel;
    }

    public void setGradeLevel(String gradeLevel) {
        this.gradeLevel = gradeLevel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
