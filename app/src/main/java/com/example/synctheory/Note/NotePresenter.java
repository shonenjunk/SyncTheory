package com.example.synctheory.Note;


public class NotePresenter implements NoteContract.Presenter{

    //presenters reference to the view
    private NoteContract.View mView;

    //required empty constructor
    public NotePresenter(){

    }

    //assigns the proper view to the presenter
    @Override
    public void setView(NoteContract.View view) { mView = view; }

    @Override
    public void start() { mView.setPresenter(this); }

    @Override
    public void stop() { }

    //method to end the note activity when the back button is pressed on the phone.
    @Override
    public void backClicked() {
        mView.finishNoteActivity();
    }
}
