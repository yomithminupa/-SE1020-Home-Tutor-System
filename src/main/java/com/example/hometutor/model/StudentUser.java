package com.example.hometutor.model;

public class StudentUser extends User {
    private String grade;
    private String address;

    public StudentUser(String id, String name, String email, String phone, String password, String grade, String address) {
        super(id, name, email, phone, password);
        this.grade = grade;
        this.address = address;
    }

    @Override
    public String getUserType() {
        return "STUDENT";
    }

    @Override
    public String displayProfile() {
        return getName() + " studies in " + grade;
    }

    @Override
    public String toFileString() {
        return String.join("|", getUserType(), commonFileFields(), clean(grade), clean(address));
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
