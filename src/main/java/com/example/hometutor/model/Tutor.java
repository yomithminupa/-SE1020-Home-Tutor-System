package com.example.hometutor.model;

public abstract class Tutor implements FileEntity {
    private String id;
    private String name;
    private String email;
    private String phone;
    private String subject;
    private String qualification;
    private String location;
    private double hourlyRate;
    private String availability;

    protected Tutor(String id, String name, String email, String phone, String subject,
                    String qualification, String location, double hourlyRate, String availability) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.subject = subject;
        this.qualification = qualification;
        this.location = location;
        this.hourlyRate = hourlyRate;
        this.availability = availability;
    }

    public abstract String getTutorType();

    public abstract double calculateSessionFee(int hours);

    public String displayCard() {
        return name + " teaches " + subject + " in " + location;
    }

    protected String clean(String value) {
        return value == null ? "" : value.replace("|", "/").replace("\n", " ").trim();
    }

    protected String commonFileFields() {
        return String.join("|",
                clean(id),
                clean(name),
                clean(email),
                clean(phone),
                clean(subject),
                clean(qualification),
                clean(location),
                String.valueOf(hourlyRate),
                clean(availability)
        );
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }
}
