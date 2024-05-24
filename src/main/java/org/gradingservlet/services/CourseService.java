package org.gradingservlet.services;

import org.gradingservlet.model.CourseDetails;
import org.gradingservlet.data.dao.interfaces.ICourseDao;
import org.gradingservlet.data.dao.interfaces.IStudentCourseDao;

import java.util.List;

public class CourseService {

    private final IStudentCourseDao studentCourseDao;
    private final ICourseDao courseDao;

    public CourseService(ICourseDao courseDao, IStudentCourseDao studentCourseDao) {
        this.courseDao = courseDao;
        this.studentCourseDao = studentCourseDao;
    }

    public CourseDetails getCourseDetails(int courseId) {
        List<Integer> marks = studentCourseDao.getCourseMarks(courseId);
        String courseName = courseDao.getCourseName(courseId);
        int marksCount = marks.size();

        if (marksCount == 0) return null;

        CourseDetails courseDetails = new CourseDetails();

        courseDetails.setCourseName(courseName);
        courseDetails.setLowestMark(marks.get(0));
        courseDetails.setHighestMark(marks.get(marksCount - 1));
        courseDetails.setRange(marks.get(marksCount - 1) - marks.get(0));
        courseDetails.setAvgMark(calcAvgMarks(marks));
        courseDetails.setMidMark(calcMidMarks(marks));
        courseDetails.setFailPercentage(calcFailPercentage(marks));
        courseDetails.setPassPercentage(calcPassPercentage(marks));

        return courseDetails;
    }

    public boolean isStudentEnrolledInCourse(int studentId, int courseId) {
        return studentCourseDao.verifyStudentCourseEnrollment( studentId, courseId );
    }

    public boolean isCourseExists(int courseId) {
        return courseDao.getCourseName( courseId ) != null;
    }




    //course statics
    private double calcAvgMarks(List<Integer> marks) {

        double result = marks.stream().
                        mapToInt(Integer::intValue)
                        .average()
                        .orElse(0);

        return roundDouble(result);
    }

    private double calcMidMarks(List<Integer> marks) {
        int size = marks.size();
        if (size == 1) return marks.get(0);

        double result = size % 2 == 1 ? marks.get(size / 2) : (marks.get(size / 2) + marks.get(size / 2 - 1)) / 2.0;

        return roundDouble(result);
    }

    private double calcFailPercentage(List<Integer> marks) {
        int totalSize = marks.size();

        double result = marks
                        .stream()
                        .filter(m -> m < 50)
                        .count() * 1.0 / totalSize;

        return roundDouble(result);
    }

    private double calcPassPercentage(List<Integer> marks) {
        return 100 - calcFailPercentage(marks);
    }

    private double roundDouble(double value) {
        double floor = Math.floor(value);

        value -= floor;

        value = Math.floor(value * 100);

        return floor + value / 100.0;

    }

}
