package org.gradingservlet.model;

public class StudentCourse {
    private int studentId;
    private int courseId;
    private String courseName;
    private int mark;

    public StudentCourse(int studentId, int courseId, String courseName, int mark) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.courseName = courseName;
        this.mark = mark;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }
}