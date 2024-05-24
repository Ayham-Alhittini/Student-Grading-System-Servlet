package org.gradingservlet.services;

import org.gradingservlet.model.Student;
import org.gradingservlet.data.dao.interfaces.IStudentDao;

public class AuthenticationService {

    private final IStudentDao studentDao;
    public AuthenticationService(IStudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public boolean isValidCredentials(String email, String password) {
        Student student = studentDao.getStudentByEmail(email);

        if (student == null) return false;

        return password.equals(student.getPassword());
    }

    public boolean isUserExists(String email) {
        return studentDao.getStudentByEmail(email) != null;
    }
}
