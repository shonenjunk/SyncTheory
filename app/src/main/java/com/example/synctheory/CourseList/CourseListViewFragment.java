package com.example.synctheory.CourseList;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.synctheory.CourseHelperClass;
import com.example.synctheory.Data.course;
import com.example.synctheory.LectureHelperClass;
import com.example.synctheory.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class CourseListViewFragment extends Fragment implements CourseListContract.View{

    private CourseListContract.Presenter mPresenter;
    private RecyclerView recyclerView;
    private FloatingActionButton btnAddCourse;

    //boolean that says if the user is a elevated user (teacher) or not (student)
    private Boolean isElevatedUser;
    courseAdapter adapter; // Create Object of the Adapter class
    DatabaseReference mBase; // Create object of the Firebase Realtime Database
    DatabaseReference mBase2;

    public CourseListViewFragment() {
        // Required empty public constructor
    }

    public static CourseListViewFragment newInstance() {
        CourseListViewFragment fragment = new CourseListViewFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_course_list_view, container, false);

        // Create a instance of the database and get
        // its reference
        mBase = FirebaseDatabase.getInstance().getReference("Courses");
        mBase2 = FirebaseDatabase.getInstance().getReference();

        recyclerView = root.findViewById(R.id.recycler1);

        // To display the Recycler view linearly
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // It is a class provide by the FirebaseUI to make a
        // query in the database to fetch appropriate data
        FirebaseRecyclerOptions<course> options
                = new FirebaseRecyclerOptions.Builder<course>()
                .setQuery(mBase, course.class)
                .build();
        // Connecting object of required Adapter class to
        // the Adapter class itself, passing in the boolean isElevated to next activity
        adapter = new courseAdapter(options, isElevatedUser);
        // Connecting Adapter class with the Recycler view*/
        recyclerView.setAdapter(adapter);

        adapter.startListening();

        btnAddCourse = root.findViewById(R.id.btnAddCourse);

        //if a user is a student (elevated user is false) hide the add class button.
        if(isElevatedUser == false){
            btnAddCourse.hide();
        }

        btnAddCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Course Name");
                final EditText input = new EditText(getContext());
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(input);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String cName = input.getText().toString();
                        //adding course to database
                        CourseHelperClass helperCourse = new CourseHelperClass(cName);
                        LectureHelperClass helperLecture = new LectureHelperClass("Lecture 1", "");
                        mBase2.child(cName).child("Lecture 1").setValue(helperLecture);
                        mBase2.child("Courses").child(cName).setValue(helperCourse);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
            }
        });

        return root;
    }

    @Override
    public void setPresenter(CourseListContract.Presenter presenter) { mPresenter = presenter; }


    @Override
    public void finishNoteActivity() {
        adapter.stopListening();
        getActivity().finish();
    }

    @Override
    public void setIsElevatedUser(Boolean isElevatedUser) {
        this.isElevatedUser = isElevatedUser;
    }
}