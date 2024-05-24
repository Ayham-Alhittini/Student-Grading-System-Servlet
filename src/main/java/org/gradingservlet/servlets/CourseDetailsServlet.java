package org.gradingservlet.servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.gradingservlet.data.dao.implementations.CourseDao;
import org.gradingservlet.data.dao.implementations.StudentCourseDao;
import org.gradingservlet.model.Student;
import org.gradingservlet.model.CourseDetails;
import org.gradingservlet.services.CourseService;
import org.gradingservlet.utilities.RequestUtility;

import java.io.IOException;

@WebServlet(name = "CourseDetailsServlet", value = "/course-details")
public class CourseDetailsServlet extends HttpServlet {

    private final CourseService courseService = new CourseService(new CourseDao(), new StudentCourseDao());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!RequestUtility.isStudentAuthenticated( request )) {
            response.sendRedirect("/login");
            return;
        }

        String courseIdParam = request.getParameter("courseId");
        if (!isValidCourseIdParam( courseIdParam, response ))
            return;

        int courseId = Integer.parseInt( courseIdParam );
        Student authenticatedStudent = RequestUtility.getAuthenticatedStudent( request );

        if (!isCourseAccessible( courseId, authenticatedStudent, response ))
            return;

        prepareAndDispatchCourseDetailsView( courseId, request, response );
    }


    private void prepareAndDispatchCourseDetailsView(int courseId, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CourseDetails courseDetails = courseService.getCourseDetails(courseId);
        request.setAttribute("courseDetails", courseDetails);
        request.getRequestDispatcher("/WEB-INF/views/course-details.jsp").forward(request, response);
    }



    // Validation methods
    private boolean isValidCourseIdParam(String courseIdParam, HttpServletResponse response) throws IOException {
        try {
            return Integer.parseInt(courseIdParam) > 0;
        } catch (Exception e) {
            response.sendError(400, "Can't resolve [" + courseIdParam + "], expect positive number.");
            return false;
        }
    }
    private boolean isCourseAccessible(int courseId, Student student, HttpServletResponse response) throws IOException {
        if (!courseService.isCourseExists(courseId)) {
            response.sendError(404, "Course not found");
            return false;
        }

        if (!courseService.isStudentEnrolledInCourse(student.getId(), courseId)) {
            response.sendError(403, "Access Denied: You must be enrolled in the course to view its details.");
            return false;
        }

        return true;
    }

}
