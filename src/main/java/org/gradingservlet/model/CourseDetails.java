package org.gradingservlet.model;

public class CourseDetails {
    private String courseName;
    private int highestMark;
    private int lowestMark;
    private double avgMark;
    private double midMark;
    private int range;
    private double passPercentage;
    private double failPercentage;


    public String getCourseName() {
        return courseName;
    }
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    public int getHighestMark() {
        return highestMark;
    }
    public void setHighestMark(int highestMark) {
        this.highestMark = highestMark;
    }
    public int getLowestMark() {
        return lowestMark;
    }
    public void setLowestMark(int lowestMark) {
        this.lowestMark = lowestMark;
    }
    public double getAvgMark() {
        return avgMark;
    }
    public void setAvgMark(double avgMark) {
        this.avgMark = avgMark;
    }
    public double getMidMark() {
        return midMark;
    }
    public void setMidMark(double midMark) {
        this.midMark = midMark;
    }
    public int getRange() {
        return range;
    }
    public void setRange(int range) {
        this.range = range;
    }
    public double getPassPercentage() {
        return passPercentage;
    }
    public void setPassPercentage(double passPercentage) {
        this.passPercentage = passPercentage;
    }
    public double getFailPercentage() {
        return failPercentage;
    }
    public void setFailPercentage(double failPercentage) {
        this.failPercentage = failPercentage;
    }
}
