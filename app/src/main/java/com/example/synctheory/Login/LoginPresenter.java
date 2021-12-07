package com.example.synctheory.Login;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginPresenter implements LoginContract.Presenter{
    private LoginContract.View mView;

    public LoginPresenter(){

    }

    @Override
    public void setView(LoginContract.View view) {
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
    public void goClicked() {

        String email = mView.getEtEmail().trim();

        String password = mView.getEtPassword().trim();

        if(!email.isEmpty() && !password.isEmpty()) {

            //checking if the user's entered email is in firebase, if so check if the password matches up
            DatabaseReference mbase = FirebaseDatabase.getInstance().getReference("Users");
            Query getUsers = mbase.child(email).orderByChild("email");

            getUsers.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        String passwordFromDb = snapshot.child("password").getValue(String.class);
                        if (passwordFromDb.equals(password)) {
                            //if username (email) is in Db with a matching password, login and pass isElevatedUser as intent extra
                            Log.d("user password matches", "logging in");
                            Boolean isElevatedUser = snapshot.child("elevatedUser").getValue(Boolean.class);
                            mView.startCourseListActivity(isElevatedUser);
                        } else {
                            Log.d("wrong password", "try again");
                            //tell the view to set password error
                            mView.setPasswordError();
                        }
                        Log.d("user found", "logging in");
                    } else {
                        Log.d("user not found", "try again");
                        mView.setEmailError();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
        /*//Check to see if Email and Password Exist and match
        if(checkUser) {
            mView.startCourseListActivity(); // if email and password exist in database, login successful, start course list activity
            Log.d("LOGIN PRESENTER", "Login Successful");
        }
        else {
            //mView.displayErrorMessage();
            Log.d("LOGIN PRESENTER", "Wrong Login Credentials");
        }*/


        //mView.startCourseListActivity();

    }

    @Override
    public void newUserClicked() {
        mView.createNewUser();
    }

}
