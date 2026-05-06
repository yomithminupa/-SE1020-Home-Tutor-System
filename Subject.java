package com.example.hometutor.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

@Entity
@Table(name = "subjects")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "subject_category", discriminatorType = DiscriminatorType.STRING)
public abstract class Subject implements IdentifiableEntity {
    @Id
    private String id;

    @Column(nullable = false)
    private String name;

    private String gradeLevel;

    @Column(length = 1000)
    private String description;

    protected Subject() {
    }

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
