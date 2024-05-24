package org.gradingservlet.services;

import org.gradingservlet.model.StudentCourse;
import org.gradingservlet.model.Student;
import org.gradingservlet.data.dao.interfaces.IStudentCourseDao;
import org.gradingservlet.data.dao.interfaces.IStudentDao;

import java.util.List;

public class StudentService {

    private final IStudentCourseDao studentCourseDao;
    private final IStudentDao studentDao;
    public StudentService(IStudentDao studentDao, IStudentCourseDao studentCourseDao) {
        this.studentDao = studentDao;
        this.studentCourseDao = studentCourseDao;
    }

    public List<StudentCourse> getStudentCourses(int studentId) {
        return studentCourseDao.getStudentCourses(studentId);
    }

    public Student getStudentByEmail(String email) {
        return studentDao.getStudentByEmail(email);
    }

}
