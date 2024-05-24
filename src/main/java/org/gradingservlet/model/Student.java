package org.gradingservlet.model;

import java.sql.ResultSet;

public class Student {
    private final int id;
    private final String name;
    private final String email;
    private final String password;
    private final String gender;
    private final String phoneNumber;
    private final String major;

    public Student(ResultSet result) {
        try {
            id = result.getInt(1);
            name = result.getString(2);
            email = result.getString(3);
            password = result.getString(4);
            gender = result.getString(5);
            phoneNumber = result.getString(6);
            major = result.getString(7);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getGender() {
        return gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getMajor() {
        return major;
    }
}
