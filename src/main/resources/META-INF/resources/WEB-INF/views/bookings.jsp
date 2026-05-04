<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*, com.example.hometutor.model.Booking" %>
<%
    List<Booking> bookings = (List<Booking>) request.getAttribute("bookings");
    if (bookings == null) {
        bookings = Collections.emptyList();
    }
    String keyword = (String) request.getAttribute("keyword");
    if (keyword == null) {
        keyword = "";
    }
%>
<%@ include file="fragments/header.jsp" %>

<section class="page-head">
    <div>
        <h2>Booking Management</h2>
        <p class="muted">Create, view, update, and cancel tutoring sessions.</p>
    </div>
    <a class="btn" href="/bookings/new">Add Booking</a>
</section>

<form class="search" method="get" action="/bookings">
    <input type="text" name="keyword" value="<%= keyword %>" placeholder="Search by booking ID, user ID, tutor ID, subject, status, or type">
    <button class="btn" type="submit">Search</button>
    <a class="btn light" href="/bookings">Clear</a>
</form>

<div class="table-wrap">
    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>User</th>
            <th>Tutor</th>
            <th>Subject</th>
            <th>Date & Time</th>
            <th>Location</th>
            <th>Status</th>
            <th>Confirmation</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <% for (Booking booking : bookings) { %>
            <tr>
                <td><%= booking.getId() %></td>
                <td><%= booking.getUserId() %></td>
                <td><%= booking.getTutorId() %></td>
                <td><%= booking.getSubject() %></td>
                <td><%= booking.getBookingDate() %> <%= booking.getBookingTime() %></td>
                <td><%= booking.getLocation() %></td>
                <td><span class="pill"><%= booking.getStatus() %></span></td>
                <td><%= booking.getConfirmationMessage() %></td>
                <td class="actions">
                    <a class="btn light" href="/bookings/edit/<%= booking.getId() %>">Edit</a>
                    <a class="btn danger" href="/bookings/delete/<%= booking.getId() %>" onclick="return confirm('Cancel this booking?')">Cancel</a>
                </td>
            </tr>
        <% } %>
        <% if (bookings.isEmpty()) { %>
            <tr><td colspan="9">No bookings found.</td></tr>
        <% } %>
        </tbody>
    </table>
</div>

<%@ include file="fragments/footer.jsp" %>
