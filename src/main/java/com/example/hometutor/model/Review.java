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
@Table(name="reviews")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "review_type", discriminatorType = DiscriminatorType.STRING)
//create a class
public abstract class Review implements IdentifiableEntity{
    //attributes
    @Id
    private String id;

    @Column(nullable = false)
    private String tutorId;

    @Column(nullable = false)
    private String userId;

    private int rating;

    @Column(length = 1000)
    private String comment;

    protected Review() {
    }
//contructor

    protected Review(String id, String tutorId, String userId, int rating, String comment) {
        this.id = id;
        this.tutorId = tutorId;
        this.userId = userId;
        this.rating = rating;
        this.comment = comment;
    }
    //methods
    public abstract String getReviewType();

    public String displayReview() {
        return rating + "/5 - " + comment;
    }
//getters and setters
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