<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.hometutor.model.*" %>
<%
    User user = (User) request.getAttribute("user");
    boolean edit = user != null;
    String type = edit ? user.getUserType() : "STUDENT";
    String grade = "";
    String address = "";
    String childName = "";
    String permissionLevel = "";
    if (user instanceof StudentUser) {
        grade = ((StudentUser) user).getGrade();
        address = ((StudentUser) user).getAddress();
    } else if (user instanceof ParentUser) {
        childName = ((ParentUser) user).getChildName();
        address = ((ParentUser) user).getAddress();
    } else if (user instanceof AdminUser) {
        permissionLevel = ((AdminUser) user).getPermissionLevel();
    }
%>
<%@ include file="fragments/header.jsp" %>

<section class="page-head">
    <div>
        <h2><%= edit ? "Update User" : "Register User" %></h2>
        <p class="muted">Use type-specific fields for student, parent, or admin users.</p>
    </div>
    <a class="btn light" href="/users">Back</a>
</section>

<form class="card" method="post" action="<%= edit ? "/users/edit/" + user.getId() : "/users" %>">
    <div class="form-grid">
        <div>
            <label>User Type</label>
            <select name="type">
                <option value="STUDENT" <%= "STUDENT".equals(type) ? "selected" : "" %>>Student</option>
                <option value="PARENT" <%= "PARENT".equals(type) ? "selected" : "" %>>Parent</option>
                <option value="ADMIN" <%= "ADMIN".equals(type) ? "selected" : "" %>>Admin</option>
            </select>
        </div>
        <div>
            <label>User ID</label>
            <input name="id" value="<%= edit ? user.getId() : "" %>" <%= edit ? "readonly" : "" %> required>
        </div>
        <div>
            <label>Name</label>
            <input name="name" value="<%= edit ? user.getName() : "" %>" required>
        </div>
        <div>
            <label>Email</label>
            <input type="email" name="email" value="<%= edit ? user.getEmail() : "" %>" required>
        </div>
        <div>
            <label>Phone</label>
            <input name="phone" value="<%= edit ? user.getPhone() : "" %>" required>
        </div>
        <div>
            <label>Password</label>
            <input type="password" name="password" value="<%= edit ? user.getPassword() : "" %>" required>
        </div>
        <div>
            <label>Grade</label>
            <input name="grade" value="<%= grade %>" placeholder="For student users">
        </div>
        <div>
            <label>Child Name</label>
            <input name="childName" value="<%= childName %>" placeholder="For parent users">
        </div>
        <div>
            <label>Address</label>
            <input name="address" value="<%= address %>">
        </div>
        <div>
            <label>Permission Level</label>
            <input name="permissionLevel" value="<%= permissionLevel %>" placeholder="For admin users">
        </div>
    </div>
    <div class="actions" style="margin-top: 18px;">
        <button class="btn" type="submit"><%= edit ? "Update User" : "Save User" %></button>
        <a class="btn light" href="/users">Cancel</a>
    </div>
</form>

<%@ include file="fragments/footer.jsp" %>
