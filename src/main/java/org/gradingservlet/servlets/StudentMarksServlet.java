package org.gradingservlet.servlets;


import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.gradingservlet.data.dao.implementations.StudentCourseDao;
import org.gradingservlet.data.dao.implementations.StudentDao;
import org.gradingservlet.model.Student;
import org.gradingservlet.model.StudentCourse;
import org.gradingservlet.services.StudentService;
import org.gradingservlet.utilities.RequestUtility;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "StudentMarks", value = "/student-marks")
public class StudentMarksServlet extends HttpServlet {

    private final StudentService studentService = new StudentService(new StudentDao(), new StudentCourseDao());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (!RequestUtility.isStudentAuthenticated(request)) {
            response.sendRedirect("/login");
            return;
        }

        prepareAndDispatchStudentMarksView(request, response);

    }

    private void prepareAndDispatchStudentMarksView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Student authenticatedStudent = RequestUtility.getAuthenticatedStudent(request);

        List<StudentCourse> studentCourses = studentService.getStudentCourses(authenticatedStudent.getId());

        request.setAttribute("studentCourses", studentCourses);

        request.getRequestDispatcher("/WEB-INF/views/student-marks.jsp").forward(request, response);
    }

}
