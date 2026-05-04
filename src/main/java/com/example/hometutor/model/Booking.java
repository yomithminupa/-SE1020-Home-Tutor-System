package com.example.hometutor.model;

public class Booking implements FileEntity {
    private String id;
    private String userId;
    private String tutorId;
    private String subject;
    private String bookingDate;
    private String bookingTime;
    private String location;
    private String status;
    private String sessionType;

    public Booking(String id, String userId, String tutorId, String subject, String bookingDate,
                   String bookingTime, String location, String status, String sessionType) {
        this.id = id;
        this.userId = userId;
        this.tutorId = tutorId;
        this.subject = subject;
        this.bookingDate = bookingDate;
        this.bookingTime = bookingTime;
        this.location = location;
        this.status = status;
        this.sessionType = sessionType;
    }

    public String getConfirmationMessage() {
        if ("ONLINE".equalsIgnoreCase(sessionType)) {
            return "Online session confirmed for " + bookingDate + " at " + bookingTime;
        }
        return "Home visit session confirmed for " + bookingDate + " at " + bookingTime;
    }

    private String clean(String value) {
        return value == null ? "" : value.replace("|", "/").replace("\n", " ").trim();
    }

    @Override
    public String toFileString() {
        return String.join("|",
                clean(id),
                clean(userId),
                clean(tutorId),
                clean(subject),
                clean(bookingDate),
                clean(bookingTime),
                clean(location),
                clean(status),
                clean(sessionType)
        );
    }

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTutorId() {
        return tutorId;
    }

    public void setTutorId(String tutorId) {
        this.tutorId = tutorId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(String bookingTime) {
        this.bookingTime = bookingTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSessionType() {
        return sessionType;
    }

    public void setSessionType(String sessionType) {
        this.sessionType = sessionType;
    }
}
