package com.example.hometutor.model;

public class VerifiedReview extends Review {
    private String bookingId;

    public VerifiedReview(String id, String tutorId, String userId, int rating, String comment, String bookingId) {
        super(id, tutorId, userId, rating, comment);
        this.bookingId = bookingId;
    }

    @Override
    public String getReviewType() {
        return "VERIFIED";
    }

    @Override
    public String displayReview() {
        return "Verified booking " + bookingId + ": " + super.displayReview();
    }

    @Override
    public String toFileString() {
        return String.join("|", getReviewType(), commonFileFields(), clean(bookingId));
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }
}
