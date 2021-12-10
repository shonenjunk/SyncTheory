package com.example.synctheory.LectureList;

// Creates constant definitions
public interface LectureListContract {
        interface View{
            void setPresenter(Presenter presenter);

            void setCourseName(String cName);

            void onResume();
            void setIsElevatedUser(Boolean isElevatedUser);
            void finishNoteActivity();
        }

        interface Presenter{
            void setView(View view);
            void start();
            void stop();

            void backClicked();
        }
}

