package com.example.hometutor.model;

public class OnlineTutor extends Tutor {
    private String platform;

    public OnlineTutor(String id, String name, String email, String phone, String subject,
                       String qualification, String location, double hourlyRate, String availability,
                       String platform) {
        super(id, name, email, phone, subject, qualification, location, hourlyRate, availability);
        this.platform = platform;
    }

    @Override
    public String getTutorType() {
        return "ONLINE";
    }

    @Override
    public double calculateSessionFee(int hours) {
        return getHourlyRate() * hours;
    }

    @Override
    public String displayCard() {
        return super.displayCard() + " online via " + platform;
    }

    @Override
    public String toFileString() {
        return String.join("|", getTutorType(), commonFileFields(), clean(platform));
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }
}
