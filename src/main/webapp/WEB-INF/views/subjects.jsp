<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*, com.example.hometutor.model.Subject" %>
<%
    List<Subject> subjects = (List<Subject>) request.getAttribute("subjects");
    if (subjects == null) {
        subjects = Collections.emptyList();
    }
    String keyword = (String) request.getAttribute("keyword");
    if (keyword == null) {
        keyword = "";
    }
%>
<%@ include file="fragments/header.jsp" %>

<section class="page-head">
    <div>
        <h2>Subject Management</h2>
        <p class="muted">Manage subjects and tuition categories.</p>
    </div>
    <a class="btn" href="/subjects/new">Add Subject</a>
</section>

<form class="search" method="get" action="/subjects">
    <input type="text" name="keyword" value="<%= keyword %>" placeholder="Search by ID, name, grade, or category">
    <button class="btn" type="submit">Search</button>
    <a class="btn light" href="/subjects">Clear</a>
</form>

<div class="table-wrap">
    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Category</th>
            <th>Grade Level</th>
            <th>Description</th>
            <th>Display Method</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <% for (Subject subject : subjects) { %>
            <tr>
                <td><%= subject.getId() %></td>
                <td><%= subject.getName() %></td>
                <td><span class="pill"><%= subject.getCategory() %></span></td>
                <td><%= subject.getGradeLevel() %></td>
                <td><%= subject.getDescription() %></td>
                <td><%= subject.displaySubject() %></td>
                <td class="actions">
                    <a class="btn light" href="/subjects/edit/<%= subject.getId() %>">Edit</a>
                    <a class="btn danger" href="/subjects/delete/<%= subject.getId() %>" onclick="return confirm('Delete this subject?')">Delete</a>
                </td>
            </tr>
        <% } %>
        <% if (subjects.isEmpty()) { %>
            <tr><td colspan="7">No subjects found.</td></tr>
        <% } %>
        </tbody>
    </table>
</div>

<%@ include file="fragments/footer.jsp" %>
