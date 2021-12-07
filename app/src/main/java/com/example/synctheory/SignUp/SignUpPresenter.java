package com.example.synctheory.SignUp;

import com.example.synctheory.UserHelperClass;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpPresenter implements SignUpContract.Presenter{

    private SignUpContract.View mView;

    public SignUpPresenter(){

    }

    @Override
    public void setView(SignUpContract.View view) {
        mView = view;
    }

    @Override
    public void start() {
        mView.setPresenter(this);
    }

    @Override
    public void stop() {

    }

    @Override
    public void signUpClicked() {
        //Get Components
        String email = mView.getEtEmail().trim();
        String phoneNumber = mView.getEtNumber().trim();
        String password = mView.getEtPassword().trim();
        Boolean checkElevUser = mView.getCheckElevUser();

        //retrieve the location of the database to store the user info
        DatabaseReference mBase = FirebaseDatabase.getInstance().getReference("Users");

        //create new user using the user helper class to initialize all user fields
        UserHelperClass helperClass = new UserHelperClass(email, phoneNumber, password, checkElevUser);

        //add the created user to the database, using the email as a primary key
        mBase.child(email).setValue(helperClass);

        //Finish activity
        mView.finishSignUpActivity();
    }

    @Override
    public void backClicked() {
        mView.finishSignUpActivity();
    }
}
