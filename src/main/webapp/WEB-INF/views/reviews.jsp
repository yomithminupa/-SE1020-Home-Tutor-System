<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*, com.example.hometutor.model.Review" %>
<%
    List<Review> reviews = (List<Review>) request.getAttribute("reviews");
    if (reviews == null) {
        reviews = Collections.emptyList();
    }
    String keyword = (String) request.getAttribute("keyword");
    if (keyword == null) {
        keyword = "";
    }
%>
<%@ include file="fragments/header.jsp" %>

<section class="page-head">
    <div>
        <h2>Review Management</h2>
        <p class="muted">Add, search, update, and delete tutor feedback.</p>
    </div>
    <a class="btn" href="/reviews/new">Add Review</a>
</section>

<form class="search" method="get" action="/reviews">
    <input type="text" name="keyword" value="<%= keyword %>" placeholder="Search by review ID, tutor ID, user ID, comment, or type">
    <button class="btn" type="submit">Search</button>
    <a class="btn light" href="/reviews">Clear</a>
</form>

<div class="table-wrap">
    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>Type</th>
            <th>Tutor</th>
            <th>User</th>
            <th>Rating</th>
            <th>Comment</th>
            <th>Display Method</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <% for (Review review : reviews) { %>
            <tr>
                <td><%= review.getId() %></td>
                <td><span class="pill"><%= review.getReviewType() %></span></td>
                <td><%= review.getTutorId() %></td>
                <td><%= review.getUserId() %></td>
                <td><%= review.getRating() %>/5</td>
                <td><%= review.getComment() %></td>
                <td><%= review.displayReview() %></td>
                <td class="actions">
                    <a class="btn light" href="/reviews/edit/<%= review.getId() %>">Edit</a>
                    <a class="btn danger" href="/reviews/delete/<%= review.getId() %>" onclick="return confirm('Delete this review?')">Delete</a>
                </td>
            </tr>
        <% } %>
        <% if (reviews.isEmpty()) { %>
            <tr><td colspan="8">No reviews found.</td></tr>
        <% } %>
        </tbody>
    </table>
</div>

<%@ include file="fragments/footer.jsp" %>
