package com.example.synctheory.SignUp;

public interface SignUpContract {

        interface View{
            void setPresenter(Presenter presenter);

            void finishSignUpActivity();

            void setEtEmail(String email);
            void setEtNumber(String number);
            void setEtPassword(String password);


            String getEtEmail();
            String getEtNumber();
            String getEtPassword();
            Boolean getCheckElevUser();
        }

        interface Presenter{
            void setView(View view);
            void start();
            void stop();

            void signUpClicked();
            void backClicked();
        }
}

