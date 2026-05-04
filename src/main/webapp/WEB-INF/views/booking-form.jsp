<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.hometutor.model.Booking" %>
<%
    Booking booking = (Booking) request.getAttribute("booking");
    boolean edit = booking != null;
%>
<%@ include file="fragments/header.jsp" %>

<section class="page-head">
    <div>
        <h2><%= edit ? "Update Booking" : "Book Tutor" %></h2>
        <p class="muted">Bookings are stored in bookings.txt.</p>
    </div>
    <a class="btn light" href="/bookings">Back</a>
</section>

<form class="card" method="post" action="<%= edit ? "/bookings/edit/" + booking.getId() : "/bookings" %>">
    <div class="form-grid">
        <div>
            <label>Booking ID</label>
            <input name="id" value="<%= edit ? booking.getId() : "" %>" <%= edit ? "readonly" : "" %> required>
        </div>
        <div>
            <label>User ID</label>
            <input name="userId" value="<%= edit ? booking.getUserId() : "" %>" required>
        </div>
        <div>
            <label>Tutor ID</label>
            <input name="tutorId" value="<%= edit ? booking.getTutorId() : "" %>" required>
        </div>
        <div>
            <label>Subject</label>
            <input name="subject" value="<%= edit ? booking.getSubject() : "" %>" required>
        </div>
        <div>
            <label>Date</label>
            <input type="date" name="bookingDate" value="<%= edit ? booking.getBookingDate() : "" %>" required>
        </div>
        <div>
            <label>Time</label>
            <input type="time" name="bookingTime" value="<%= edit ? booking.getBookingTime() : "" %>" required>
        </div>
        <div>
            <label>Location</label>
            <input name="location" value="<%= edit ? booking.getLocation() : "" %>" required>
        </div>
        <div>
            <label>Status</label>
            <select name="status">
                <option value="PENDING" <%= edit && "PENDING".equals(booking.getStatus()) ? "selected" : "" %>>Pending</option>
                <option value="APPROVED" <%= edit && "APPROVED".equals(booking.getStatus()) ? "selected" : "" %>>Approved</option>
                <option value="COMPLETED" <%= edit && "COMPLETED".equals(booking.getStatus()) ? "selected" : "" %>>Completed</option>
                <option value="CANCELLED" <%= edit && "CANCELLED".equals(booking.getStatus()) ? "selected" : "" %>>Cancelled</option>
            </select>
        </div>
        <div>
            <label>Session Type</label>
            <select name="sessionType">
                <option value="ONLINE" <%= edit && "ONLINE".equals(booking.getSessionType()) ? "selected" : "" %>>Online</option>
                <option value="HOME_VISIT" <%= edit && "HOME_VISIT".equals(booking.getSessionType()) ? "selected" : "" %>>Home Visit</option>
            </select>
        </div>
    </div>
    <div class="actions" style="margin-top: 18px;">
        <button class="btn" type="submit"><%= edit ? "Update Booking" : "Save Booking" %></button>
        <a class="btn light" href="/bookings">Cancel</a>
    </div>
</form>

<%@ include file="fragments/footer.jsp" %>
