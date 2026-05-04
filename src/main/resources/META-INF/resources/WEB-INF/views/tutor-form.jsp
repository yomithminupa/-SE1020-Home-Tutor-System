<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.hometutor.model.*" %>
<%
    Tutor tutor = (Tutor) request.getAttribute("tutor");
    boolean edit = tutor != null;
    String type = edit ? tutor.getTutorType() : "ONLINE";
    String platform = "";
    String travelFee = "";
    if (tutor instanceof OnlineTutor) {
        platform = ((OnlineTutor) tutor).getPlatform();
    } else if (tutor instanceof HomeVisitTutor) {
        travelFee = String.valueOf(((HomeVisitTutor) tutor).getTravelFee());
    }
%>
<%@ include file="fragments/header.jsp" %>

<section class="page-head">
    <div>
        <h2><%= edit ? "Update Tutor" : "Add Tutor" %></h2>
        <p class="muted">Online tutors use a platform field; home visit tutors use a travel fee.</p>
    </div>
    <a class="btn light" href="/tutors">Back</a>
</section>

<form class="card" method="post" action="<%= edit ? "/tutors/edit/" + tutor.getId() : "/tutors" %>">
    <div class="form-grid">
        <div>
            <label>Tutor Type</label>
            <select name="type">
                <option value="ONLINE" <%= "ONLINE".equals(type) ? "selected" : "" %>>Online</option>
                <option value="HOME_VISIT" <%= "HOME_VISIT".equals(type) ? "selected" : "" %>>Home Visit</option>
            </select>
        </div>
        <div>
            <label>Tutor ID</label>
            <input name="id" value="<%= edit ? tutor.getId() : "" %>" <%= edit ? "readonly" : "" %> required>
        </div>
        <div>
            <label>Name</label>
            <input name="name" value="<%= edit ? tutor.getName() : "" %>" required>
        </div>
        <div>
            <label>Email</label>
            <input type="email" name="email" value="<%= edit ? tutor.getEmail() : "" %>" required>
        </div>
        <div>
            <label>Phone</label>
            <input name="phone" value="<%= edit ? tutor.getPhone() : "" %>" required>
        </div>
        <div>
            <label>Subject</label>
            <input name="subject" value="<%= edit ? tutor.getSubject() : "" %>" required>
        </div>
        <div>
            <label>Qualification</label>
            <input name="qualification" value="<%= edit ? tutor.getQualification() : "" %>" required>
        </div>
        <div>
            <label>Location</label>
            <input name="location" value="<%= edit ? tutor.getLocation() : "" %>" required>
        </div>
        <div>
            <label>Hourly Rate</label>
            <input type="number" step="0.01" name="hourlyRate" value="<%= edit ? tutor.getHourlyRate() : "" %>" required>
        </div>
        <div>
            <label>Availability</label>
            <input name="availability" value="<%= edit ? tutor.getAvailability() : "" %>" placeholder="Available / Busy / Weekends" required>
        </div>
        <div>
            <label>Online Platform</label>
            <input name="platform" value="<%= platform %>" placeholder="Zoom, Google Meet, Teams">
        </div>
        <div>
            <label>Travel Fee</label>
            <input type="number" step="0.01" name="travelFee" value="<%= travelFee %>" placeholder="For home visit tutors">
        </div>
    </div>
    <div class="actions" style="margin-top: 18px;">
        <button class="btn" type="submit"><%= edit ? "Update Tutor" : "Save Tutor" %></button>
        <a class="btn light" href="/tutors">Cancel</a>
    </div>
</form>

<%@ include file="fragments/footer.jsp" %>
