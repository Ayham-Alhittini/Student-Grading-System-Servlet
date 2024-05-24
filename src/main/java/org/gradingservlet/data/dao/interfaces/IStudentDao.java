package org.gradingservlet.data.dao.interfaces;

import org.gradingservlet.model.Student;

public interface IStudentDao {
    Student getStudentByEmail(String email);
}
