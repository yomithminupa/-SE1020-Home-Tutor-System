<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*, com.example.hometutor.model.User" %>
<%
    List<User> users = (List<User>) request.getAttribute("users");
    if (users == null) {
        users = Collections.emptyList();
    }
    String keyword = (String) request.getAttribute("keyword");
    if (keyword == null) {
        keyword = "";
    }
%>
<%@ include file="fragments/header.jsp" %>

<section class="page-head">
    <div>
        <h2>User Management</h2>
        <p class="muted">Create, search, update, and delete students, parents, and admins.</p>
    </div>
    <a class="btn" href="/users/new">Add User</a>
</section>

<form class="search" method="get" action="/users">
    <input type="text" name="keyword" value="<%= keyword %>" placeholder="Search by ID, name, email, phone, or type">
    <button class="btn" type="submit">Search</button>
    <a class="btn light" href="/users">Clear</a>
</form>

<div class="table-wrap">
    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Type</th>
            <th>Email</th>
            <th>Phone</th>
            <th>Display Method</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <% for (User user : users) { %>
            <tr>
                <td><%= user.getId() %></td>
                <td><%= user.getName() %></td>
                <td><span class="pill"><%= user.getUserType() %></span></td>
                <td><%= user.getEmail() %></td>
                <td><%= user.getPhone() %></td>
                <td><%= user.displayProfile() %></td>
                <td class="actions">
                    <a class="btn light" href="/users/edit/<%= user.getId() %>">Edit</a>
                    <a class="btn danger" href="/users/delete/<%= user.getId() %>" onclick="return confirm('Delete this user?')">Delete</a>
                </td>
            </tr>
        <% } %>
        <% if (users.isEmpty()) { %>
            <tr><td colspan="7">No users found.</td></tr>
        <% } %>
        </tbody>
    </table>
</div>

<%@ include file="fragments/footer.jsp" %>
