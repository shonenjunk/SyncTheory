package com.example.synctheory;

public class LectureHelperClass {

    String lectureName;
    String note;

    public LectureHelperClass(){
    }

    public LectureHelperClass(String lectureName, String note) {
        this.lectureName = lectureName;
        this.note = note;
    }

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
