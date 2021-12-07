package com.example.synctheory.CourseList;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import com.example.synctheory.R;
import android.view.WindowManager;

public class CourseListActivity extends AppCompatActivity {

    CourseListContract.View mView;
    CourseListContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_course_list);
        mView = (CourseListContract.View) getSupportFragmentManager().findFragmentById(R.id.fragmentCourseListView);
        mPresenter = new CourseListPresenter();
        mPresenter.setView(mView);

        Intent callingIntent = this.getIntent();
        Boolean isElevatedUser = callingIntent.getBooleanExtra("isElevatedUser", false); //retrieves the course name that was clicked on
        mView.setIsElevatedUser(isElevatedUser);
        getSupportActionBar().setTitle("Courses"); // set the top title
    }

    // Function to tell the app to start getting
    // data from database on starting of the activity
    @Override protected void onStart()
    {
        super.onStart();
    }

    // Function to tell the app to stop getting
    // data from database on stopping of the activity
    @Override protected void onStop()
    {
        super.onStop();
    }

}
