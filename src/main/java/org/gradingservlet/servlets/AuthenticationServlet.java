package org.gradingservlet.servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.gradingservlet.data.dao.implementations.StudentCourseDao;
import org.gradingservlet.data.dao.implementations.StudentDao;
import org.gradingservlet.data.dao.interfaces.IStudentCourseDao;
import org.gradingservlet.data.dao.interfaces.IStudentDao;
import org.gradingservlet.model.Student;
import org.gradingservlet.services.AuthenticationService;
import org.gradingservlet.services.StudentService;
import java.io.IOException;

@WebServlet(name = "AuthenticationServlet", value = "/login")
public class AuthenticationServlet extends HttpServlet {

    private AuthenticationService authenticationService;
    private StudentService studentService;

    @Override
    public void init() {
        IStudentDao studentDao = new StudentDao();
        IStudentCourseDao studentCourseDao = new StudentCourseDao();

        authenticationService = new AuthenticationService(studentDao);
        studentService = new StudentService(studentDao, studentCourseDao);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if( authenticationService.isValidCredentials( email, password ) ){

            Student student = studentService.getStudentByEmail(email);

            HttpSession session = request.getSession();
            session.setAttribute("student", student);

            response.sendRedirect("/student-marks");

        }else{

            request.setAttribute("loginStatus", "Invalid Credentials!!");
            request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request,response);

        }

    }
}
