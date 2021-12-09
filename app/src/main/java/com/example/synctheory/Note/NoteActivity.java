package com.example.synctheory.Note;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.example.synctheory.SignUp.SignUpContract;
import com.example.synctheory.SignUp.SignUpPresenter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.example.synctheory.R;

public class NoteActivity extends AppCompatActivity {

    NoteContract.View mView;
    NoteContract.Presenter mPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_note);
        mView = (NoteContract.View) getSupportFragmentManager().findFragmentById(R.id.fragmentNoteView);
        mPresenter = new NotePresenter();
        mPresenter.setView(mView);

        //Handle Action Bar Settings
        Intent callingIntent = this.getIntent();
        String course = callingIntent.getStringExtra("CourseName");
        String lectureName = callingIntent.getStringExtra("LectureName");
        getSupportActionBar().setTitle(course + ": " + lectureName); // set the top title

        //Set View course and lecture name, given from the intents of the previous lecture activity.
        mView.setCourseName(course);
        mView.setLectureName(lectureName);
    }


    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.start();
    }
}