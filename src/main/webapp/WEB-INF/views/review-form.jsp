<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.hometutor.model.*" %>
<%
    Review review = (Review) request.getAttribute("review");
    boolean edit = review != null;
    String type = edit ? review.getReviewType() : "PUBLIC";
    String nickname = "";
    String bookingId = "";
    if (review instanceof PublicReview) {
        nickname = ((PublicReview) review).getNickname();
    } else if (review instanceof VerifiedReview) {
        bookingId = ((VerifiedReview) review).getBookingId();
    }
%>
<%@ include file="fragments/header.jsp" %>

<section class="page-head">
    <div>
        <h2><%= edit ? "Update Review" : "Add Review" %></h2>
        <p class="muted">Reviews are stored in reviews.txt.</p>
    </div>
    <a class="btn light" href="/reviews">Back</a>
</section>

<form class="card" method="post" action="<%= edit ? "/reviews/edit/" + review.getId() : "/reviews" %>">
    <div class="form-grid">
        <div>
            <label>Review Type</label>
            <select name="type">
                <option value="PUBLIC" <%= "PUBLIC".equals(type) ? "selected" : "" %>>Public</option>
                <option value="VERIFIED" <%= "VERIFIED".equals(type) ? "selected" : "" %>>Verified</option>
            </select>
        </div>
        <div>
            <label>Review ID</label>
            <input name="id" value="<%= edit ? review.getId() : "" %>" <%= edit ? "readonly" : "" %> required>
        </div>
        <div>
            <label>Tutor ID</label>
            <input name="tutorId" value="<%= edit ? review.getTutorId() : "" %>" required>
        </div>
        <div>
            <label>User ID</label>
            <input name="userId" value="<%= edit ? review.getUserId() : "" %>" required>
        </div>
        <div>
            <label>Rating</label>
            <input type="number" min="1" max="5" name="rating" value="<%= edit ? review.getRating() : 5 %>" required>
        </div>
        <div>
            <label>Nickname</label>
            <input name="nickname" value="<%= nickname %>" placeholder="For public reviews">
        </div>
        <div>
            <label>Booking ID</label>
            <input name="bookingId" value="<%= bookingId %>" placeholder="For verified reviews">
        </div>
    </div>
    <label>Comment</label>
    <textarea name="comment" required><%= edit ? review.getComment() : "" %></textarea>
    <div class="actions" style="margin-top: 18px;">
        <button class="btn" type="submit"><%= edit ? "Update Review" : "Save Review" %></button>
        <a class="btn light" href="/reviews">Cancel</a>
    </div>
</form>

<%@ include file="fragments/footer.jsp" %>
