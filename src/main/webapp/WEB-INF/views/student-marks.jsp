<%@ page import="org.gradingservlet.model.Student" %>
<%@ page import="java.util.List" %>
<%@ page import="org.gradingservlet.model.StudentCourse" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    Student student = (Student) session.getAttribute("student");
    List<StudentCourse> studentCourses =  (List<StudentCourse>) request.getAttribute("studentCourses");
%>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../styles/student-marks.css">
    <title>Student Marks Page</title>
</head>
<body>

<div class="container">
    <header class="student-header">
        <h1>Student Marks Dashboard</h1>
        <div class="student-name">Student Name: <span><%=student.getName()%></span></div>
    </header>

    <section class="marks-table">
        <table id="marks">
            <thead>
            <tr>
                <th>Course ID</th>
                <th>Course Name</th>
                <th>Mark</th>
                <th>Details</th>
            </tr>
            </thead>
            <tbody>

            <%   for( StudentCourse course: studentCourses ){ %>
                <tr>
                    <td><%= course.getCourseId() %></td>
                    <td><%= course.getCourseName() %></td>
                    <td><%= course.getMark() %>%</td>
                    <td><a href="course-details?courseId=<%= course.getCourseId() %>">View Details</a></td>
                </tr>
            <%   } %>


            </tbody>
        </table>
    </section>
</div>

</body>
</html>

<style>
    <%@include file="../styles/student-marks.css"%>
</style>
