package com.example.synctheory.Note;

public interface NoteContract {
        interface View{
            void setPresenter(Presenter presenter);

            void setCourseName(String cName);
            void setLectureName(String lName);

            void onResume();
            void finishNoteActivity();
        }

        interface Presenter{
            void setView(View view);
            void start();
            void stop();

            void backClicked();
        }
}

