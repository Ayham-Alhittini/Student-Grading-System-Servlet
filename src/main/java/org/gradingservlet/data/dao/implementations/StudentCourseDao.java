package org.gradingservlet.data.dao.implementations;

import org.gradingservlet.data.DbConnection;
import org.gradingservlet.model.StudentCourse;
import org.gradingservlet.data.dao.interfaces.IStudentCourseDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentCourseDao implements IStudentCourseDao {

    @Override
    public List<StudentCourse> getStudentCourses(int studentId) {
        try {
            String query =
                    "SELECT " +
                    "    courses.id, " +
                    "    courses.name, " +
                    "    student_courses.mark " +
                    "FROM " +
                    "    (SELECT * FROM students_courses WHERE student_id = ?) AS student_courses " +
                    "INNER JOIN courses " +
                    "    ON student_courses.course_id = courses.id;";


            PreparedStatement stmt = DbConnection.createPreparedStatement(query);

            stmt.setInt(1, studentId);

            ResultSet result = stmt.executeQuery();

            List<StudentCourse> courses = new ArrayList<>();

            while (result.next()) {
                courses.add(new StudentCourse(
                   studentId,
                    result.getInt("id"),
                    result.getString("name"),
                    result.getInt("mark")
                ));
            }

            DbConnection.closeConnection();

            return courses;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Integer> getCourseMarks(int courseId) {

        try {
            String query =
                    "SELECT mark " +
                    "FROM students_courses " +
                    "WHERE Course_id = ? " +
                    "ORDER BY mark ASC;";


            PreparedStatement stmt = DbConnection.createPreparedStatement(query);

            stmt.setInt(1, courseId);

            ResultSet result = stmt.executeQuery();

            List<Integer> marks = new ArrayList<>();

            while (result.next())
                marks.add(result.getInt("mark"));

            DbConnection.closeConnection();

            return marks;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean verifyStudentCourseEnrollment(int studentId, int courseId) {
        try {
            String query =
                    "SELECT * " +
                    "FROM students_courses " +
                    "WHERE student_id = ? AND course_id = ?";


            PreparedStatement stmt = DbConnection.createPreparedStatement(query);

            stmt.setInt(1, studentId);
            stmt.setInt(2, courseId);

            ResultSet result = stmt.executeQuery();

            boolean isEnrolled = result.next();

            DbConnection.closeConnection();

            return isEnrolled;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
