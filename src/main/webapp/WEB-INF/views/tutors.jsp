<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*, com.example.hometutor.model.Tutor" %>
<%
    List<Tutor> tutors = (List<Tutor>) request.getAttribute("tutors");
    if (tutors == null) {
        tutors = Collections.emptyList();
    }
    String keyword = (String) request.getAttribute("keyword");
    if (keyword == null) {
        keyword = "";
    }
%>
<%@ include file="fragments/header.jsp" %>

<section class="page-head">
    <div>
        <h2>Tutor Management</h2>
        <p class="muted">Manage online and home visit tutor profiles.</p>
    </div>
    <a class="btn" href="/tutors/new">Add Tutor</a>
</section>

<form class="search" method="get" action="/tutors">
    <input type="text" name="keyword" value="<%= keyword %>" placeholder="Search by name, subject, location, type, or availability">
    <button class="btn" type="submit">Search</button>
    <a class="btn light" href="/tutors">Clear</a>
</form>

<div class="table-wrap">
    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Type</th>
            <th>Subject</th>
            <th>Location</th>
            <th>Rate</th>
            <th>Availability</th>
            <th>Sample Fee</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <% for (Tutor tutor : tutors) { %>
            <tr>
                <td><%= tutor.getId() %></td>
                <td><%= tutor.getName() %><br><span class="muted"><%= tutor.getQualification() %></span></td>
                <td><span class="pill"><%= tutor.getTutorType() %></span></td>
                <td><%= tutor.getSubject() %></td>
                <td><%= tutor.getLocation() %></td>
                <td>Rs. <%= tutor.getHourlyRate() %></td>
                <td><%= tutor.getAvailability() %></td>
                <td>Rs. <%= tutor.calculateSessionFee(2) %> for 2h</td>
                <td class="actions">
                    <a class="btn light" href="/tutors/edit/<%= tutor.getId() %>">Edit</a>
                    <a class="btn danger" href="/tutors/delete/<%= tutor.getId() %>" onclick="return confirm('Delete this tutor?')">Delete</a>
                </td>
            </tr>
        <% } %>
        <% if (tutors.isEmpty()) { %>
            <tr><td colspan="9">No tutors found.</td></tr>
        <% } %>
        </tbody>
    </table>
</div>

<%@ include file="fragments/footer.jsp" %>
