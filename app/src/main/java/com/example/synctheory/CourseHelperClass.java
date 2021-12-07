package com.example.synctheory;

public class CourseHelperClass {

    String courseName;

    public CourseHelperClass(){
    }

    public CourseHelperClass(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}
