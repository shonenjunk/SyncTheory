package com.example.synctheory.LectureList;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.synctheory.CourseList.courseAdapter;
import com.example.synctheory.Data.lecture;
import com.example.synctheory.Note.NoteContract;
import com.example.synctheory.Note.NotePresenter;
import com.example.synctheory.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import com.google.firebase.database.Query;



public class LectureListActivity extends AppCompatActivity {

    LectureListContract.View mView;
    LectureListContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_lecture_list);
        mView = (LectureListContract.View) getSupportFragmentManager().findFragmentById(R.id.fragmentLectureListView);
        mPresenter = new LectureListPresenter();
        mPresenter.setView(mView);

        Intent callingIntent = this.getIntent();
        String course = callingIntent.getStringExtra("CourseName"); //retrieves the course name that was clicked on
        Boolean isElevatedUser = callingIntent.getBooleanExtra("isElevatedUser", false);
        mView.setIsElevatedUser(isElevatedUser);
        getSupportActionBar().setTitle(course); // set the top title

        //Set View course name
        mView.setCourseName(course);
    }

    // Function to tell the app to start getting
    // data from database on starting of the activity
    @Override
    protected void onStart()
    {
        super.onStart();
        mPresenter.start();
    }

    // Function to tell the app to stop getting
    // data from database on stopping of the activity
    @Override
    protected void onStop()
    {
        super.onStop();
    }
}
