package com.example.synctheory;

//helper class that helps encode newly added courses to the proper JSON format required by Firebase
public class CourseHelperClass {

    //course name
    String courseName;

    //required empty constructor
    public CourseHelperClass(){
    }

    //constructor called to create the properly encoded course object based on the course name given by the teacher.
    public CourseHelperClass(String courseName) {
        this.courseName = courseName;
    }

    //getter and setter for the course name (unused)
    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}
