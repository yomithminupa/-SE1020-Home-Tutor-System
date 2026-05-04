package com.example.hometutor.model;

public abstract class Review implements FileEntity {
    private String id;
    private String tutorId;
    private String userId;
    private int rating;
    private String comment;

    protected Review(String id, String tutorId, String userId, int rating, String comment) {
        this.id = id;
        this.tutorId = tutorId;
        this.userId = userId;
        this.rating = rating;
        this.comment = comment;
    }

    public abstract String getReviewType();

    public String displayReview() {
        return rating + "/5 - " + comment;
    }

    protected String clean(String value) {
        return value == null ? "" : value.replace("|", "/").replace("\n", " ").trim();
    }

    protected String commonFileFields() {
        return String.join("|", clean(id), clean(tutorId), clean(userId), String.valueOf(rating), clean(comment));
    }

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTutorId() {
        return tutorId;
    }

    public void setTutorId(String tutorId) {
        this.tutorId = tutorId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
