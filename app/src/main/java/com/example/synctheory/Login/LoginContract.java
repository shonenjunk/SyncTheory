package com.example.synctheory.Login;

public interface LoginContract {

    interface View{
        void setPresenter(Presenter presenter);
        void createNewUser();
        void startCourseListActivity(Boolean isElevatedUser);

        String getEtEmail();
        String getEtPassword();

        void setEtEmail(String email);
        void setEtPassword(String password);

        void setPasswordError();
        void setEmailError();
    }

    interface Presenter{
        void setView(View view);
        void start();
        void stop();

        void goClicked();
        void newUserClicked();
    }
}
