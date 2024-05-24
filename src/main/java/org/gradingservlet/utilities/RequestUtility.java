package org.gradingservlet.utilities;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.gradingservlet.model.Student;

public class RequestUtility {
    public static boolean isStudentAuthenticated(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return session.getAttribute("student") != null;
    }

    public static Student getAuthenticatedStudent(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return (Student) session.getAttribute("student");
    }

}
