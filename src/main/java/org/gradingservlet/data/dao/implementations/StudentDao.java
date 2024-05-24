package org.gradingservlet.data.dao.implementations;

import org.gradingservlet.data.dao.interfaces.IStudentDao;
import org.gradingservlet.data.DbConnection;
import org.gradingservlet.model.Student;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentDao implements IStudentDao {

    @Override
    public Student getStudentByEmail(String email) {
        try {

            String query =
                    "SELECT * " +
                    "FROM students " +
                    "WHERE email = ?";


            PreparedStatement stmt = DbConnection.createPreparedStatement(query);

            stmt.setString(1, email);

            ResultSet result = stmt.executeQuery();

            Student student = result.next() ? new Student(result) : null;

            DbConnection.closeConnection();

            return student;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}