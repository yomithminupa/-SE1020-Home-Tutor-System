package com.example.hometutor.model;

public class PublicReview extends Review {
    private String nickname;

    public PublicReview(String id, String tutorId, String userId, int rating, String comment, String nickname) {
        super(id, tutorId, userId, rating, comment);
        this.nickname = nickname;
    }

    @Override
    public String getReviewType() {
        return "PUBLIC";
    }

    @Override
    public String displayReview() {
        return nickname + ": " + super.displayReview();
    }

    @Override
    public String toFileString() {
        return String.join("|", getReviewType(), commonFileFields(), clean(nickname));
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
