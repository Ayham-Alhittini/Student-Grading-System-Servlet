package org.gradingservlet.data.dao.implementations;

import org.gradingservlet.data.dao.interfaces.ICourseDao;
import org.gradingservlet.data.DbConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseDao implements ICourseDao {
    @Override
    public String getCourseName(int courseId) {
        try {
            String query =
                    "SELECT name " +
                    "FROM courses " +
                    "WHERE id = ?";


            PreparedStatement stmt = DbConnection.createPreparedStatement(query);

            stmt.setInt(1, courseId);

            ResultSet result = stmt.executeQuery();

            String courseName = result.next() ? result.getString("name") : null;

            DbConnection.closeConnection();

            return courseName;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}