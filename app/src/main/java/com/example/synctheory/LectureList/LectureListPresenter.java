package com.example.synctheory.LectureList;

public class LectureListPresenter implements LectureListContract.Presenter{

    private LectureListContract.View mView;

    public LectureListPresenter(){

    }

    @Override
    public void setView(LectureListContract.View view) { mView = view; }

    @Override
    public void start() { mView.setPresenter(this); }

    @Override
    public void stop() { }

    @Override
    public void backClicked() { mView.finishNoteActivity(); }
}
