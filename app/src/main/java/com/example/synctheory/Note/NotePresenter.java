package com.example.synctheory.Note;


public class NotePresenter implements NoteContract.Presenter{

    private NoteContract.View mView;

    public NotePresenter(){

    }

    @Override
    public void setView(NoteContract.View view) { mView = view; }

    @Override
    public void start() { mView.setPresenter(this); }

    @Override
    public void stop() { }

    @Override
    public void backClicked() {
        mView.finishNoteActivity();
    }
}
