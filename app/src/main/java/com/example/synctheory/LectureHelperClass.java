package com.example.synctheory;

//helper class that helps encode newly added lectures to the proper JSON format required by Firebase
public class LectureHelperClass {

    //lecture data
    String lectureName;
    String note;

    //required empty constructor
    public LectureHelperClass(){
    }

    //constructor called to create the lecture object based on the lecture name given by the teacher
    public LectureHelperClass(String lectureName, String note) {
        this.lectureName = lectureName;
        this.note = note;
    }

    //general getters and setters for each of the various lecture attributes (unused)
    public String getLectureName() {
        return lectureName;
    }

    public void setLectureName(String lectureName) {
        this.lectureName = lectureName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
