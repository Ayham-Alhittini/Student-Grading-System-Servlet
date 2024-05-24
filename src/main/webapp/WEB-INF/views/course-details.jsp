<%@ page import="org.gradingservlet.model.CourseDetails" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    CourseDetails courseDetails = (CourseDetails) request.getAttribute("courseDetails");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../styles/course-details.css">
    <title>Course Details</title>
</head>
<body>

<div class="container">
    <header class="course-header">
        <h1 id="courseName"><%= courseDetails.getCourseName() %></h1>
    </header>

    <div class="card-container">
        <div class="card">
            <h2>Average Mark</h2>
            <p id="averageMark"><%= courseDetails.getAvgMark() %>%</p>
        </div>

        <div class="card">
            <h2>Median Mark</h2>
            <p id="medianMark"><%= courseDetails.getMidMark() %>%</p>
        </div>

        <div class="card">
            <h2>Lowest Mark</h2>
            <p id="lowestMark"><%= courseDetails.getLowestMark() %>%</p>
        </div>

        <div class="card">
            <h2>Highest Mark</h2>
            <p id="highestMark"><%= courseDetails.getHighestMark() %>%</p>
        </div>

        <div class="card">
            <h2>Mark Range</h2>
            <p id="markRange"><%= courseDetails.getRange() %>%</p>
        </div>

        <div class="card">
            <h2>Student Pass Percentage</h2>
            <p id="passPercentage"><%= courseDetails.getPassPercentage() %>%</p>
        </div>

        <div class="card">
            <h2>Fail Student Percentage</h2>
            <p id="failPercentage"><%= courseDetails.getFailPercentage() %>%</p>
        </div>
    </div>
</div>

</body>
</html>

<style>
    <%@include file="../styles/course-details.css"%>
</style>
