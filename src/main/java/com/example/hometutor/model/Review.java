package com.example.hometutor.model;

public class Review {

    private String id;
    private String studentName;
    private String tutorName;
    private int starRating;
    private String comment;
    private String date;

    public Review() {}

    public Review(String id, String studentName, String tutorName,
                  int starRating, String comment, String date) {
        this.id = id;
        this.studentName = studentName;
        this.tutorName = tutorName;
        this.starRating = starRating;
        this.comment = comment;
        this.date = date;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }
    public String getTutorName() { return tutorName; }
    public void setTutorName(String tutorName) { this.tutorName = tutorName; }
    public int getStarRating() { return starRating; }
    public void setStarRating(int starRating) { this.starRating = starRating; }
    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
}