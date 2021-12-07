package com.example.synctheory.CourseList;

public interface CourseListContract {
        interface View{
            void setPresenter(Presenter presenter);

            void onResume();
            void finishNoteActivity();
            void setIsElevatedUser(Boolean isElevatedUser);
        }

        interface Presenter{
            void setView(View view);
            void start();
            void stop();

            void backClicked();
        }
}

