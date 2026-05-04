<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.hometutor.model.*" %>
<%
    Subject subject = (Subject) request.getAttribute("subject");
    boolean edit = subject != null;
    String category = edit ? subject.getCategory() : "ACADEMIC";
    String stream = "";
    String skillLevel = "";
    if (subject instanceof AcademicSubject) {
        stream = ((AcademicSubject) subject).getStream();
    } else if (subject instanceof SkillSubject) {
        skillLevel = ((SkillSubject) subject).getSkillLevel();
    }
%>
<%@ include file="fragments/header.jsp" %>

<section class="page-head">
    <div>
        <h2><%= edit ? "Update Subject" : "Add Subject" %></h2>
        <p class="muted">Subjects are stored in subjects.txt.</p>
    </div>
    <a class="btn light" href="/subjects">Back</a>
</section>

<form class="card" method="post" action="<%= edit ? "/subjects/edit/" + subject.getId() : "/subjects" %>">
    <div class="form-grid">
        <div>
            <label>Category</label>
            <select name="category">
                <option value="ACADEMIC" <%= "ACADEMIC".equals(category) ? "selected" : "" %>>Academic</option>
                <option value="SKILL" <%= "SKILL".equals(category) ? "selected" : "" %>>Skill</option>
            </select>
        </div>
        <div>
            <label>Subject ID</label>
            <input name="id" value="<%= edit ? subject.getId() : "" %>" <%= edit ? "readonly" : "" %> required>
        </div>
        <div>
            <label>Name</label>
            <input name="name" value="<%= edit ? subject.getName() : "" %>" required>
        </div>
        <div>
            <label>Grade Level</label>
            <input name="gradeLevel" value="<%= edit ? subject.getGradeLevel() : "" %>" required>
        </div>
        <div>
            <label>Stream</label>
            <input name="stream" value="<%= stream %>" placeholder="For academic subjects">
        </div>
        <div>
            <label>Skill Level</label>
            <input name="skillLevel" value="<%= skillLevel %>" placeholder="For skill subjects">
        </div>
    </div>
    <label>Description</label>
    <textarea name="description" required><%= edit ? subject.getDescription() : "" %></textarea>
    <div class="actions" style="margin-top: 18px;">
        <button class="btn" type="submit"><%= edit ? "Update Subject" : "Save Subject" %></button>
        <a class="btn light" href="/subjects">Cancel</a>
    </div>
</form>

<%@ include file="fragments/footer.jsp" %>
