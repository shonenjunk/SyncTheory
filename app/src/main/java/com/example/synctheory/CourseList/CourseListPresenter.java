package com.example.synctheory.CourseList;

public class CourseListPresenter implements CourseListContract.Presenter{

    private CourseListContract.View mView;

    @Override
    public void setView(CourseListContract.View view) { mView = view; }

    @Override
    public void start() { mView.setPresenter(this); }

    @Override
    public void stop() { }

    @Override
    public void backClicked() { mView.finishNoteActivity(); }
}
