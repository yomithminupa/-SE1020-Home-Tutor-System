package com.example.hometutor.model;

public class SkillSubject extends Subject {
    private String skillLevel;

    public SkillSubject(String id, String name, String gradeLevel, String description, String skillLevel) {
        super(id, name, gradeLevel, description);
        this.skillLevel = skillLevel;
    }

    @Override
    public String getCategory() {
        return "SKILL";
    }

    @Override
    public String displaySubject() {
        return getName() + " - " + skillLevel + " level";
    }

    @Override
    public String toFileString() {
        return String.join("|", getCategory(), commonFileFields(), clean(skillLevel));
    }

    public String getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(String skillLevel) {
        this.skillLevel = skillLevel;
    }
}
