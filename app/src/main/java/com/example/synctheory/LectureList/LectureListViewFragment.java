package com.example.synctheory.LectureList;

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

import com.example.synctheory.Data.lecture;
import com.example.synctheory.LectureHelperClass;
import com.example.synctheory.Note.NoteContract;
import com.example.synctheory.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class LectureListViewFragment extends Fragment implements LectureListContract.View{

    private LectureListContract.Presenter mPresenter;
    private RecyclerView recyclerView;
    private FloatingActionButton btnAddLecture;

    //boolean that says if the user is a elevated user (teacher) or not (student)
    private Boolean isElevatedUser;

    lectureAdapter adapter; // Create Object of the Adapter class
    DatabaseReference mBase; // Create object of the Firebase Realtime Database
    String course;

    public LectureListViewFragment() {
        // Required empty public constructor
    }

    public static LectureListViewFragment newInstance(String param1, String param2) {
        LectureListViewFragment fragment = new LectureListViewFragment();
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
        View root =  inflater.inflate(R.layout.fragment_lecture_list_view, container, false);

        // Create a instance of the database and get its reference
        mBase = FirebaseDatabase.getInstance().getReference(course);
        recyclerView = root.findViewById(R.id.recycler2);

        // To display the Recycler view linearly
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // It is a class provide by the FirebaseUI to make a query in the database to fetch appropriate data
        FirebaseRecyclerOptions<lecture> options = new FirebaseRecyclerOptions.Builder<lecture>().setQuery(mBase, lecture.class).build();

        // Connecting object of required Adapter class to
        // the Adapter class itself
        adapter = new lectureAdapter(options, course);

        // Connecting Adapter class with the Recycler view
        recyclerView.setAdapter(adapter);

        adapter.startListening();

        btnAddLecture = root.findViewById(R.id.btnAddLecture);

        if(isElevatedUser == false){
            btnAddLecture.hide();
        }

        btnAddLecture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Lecture Name");
                final EditText input = new EditText(getContext());
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(input);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String lName = input.getText().toString();
                        //adding new lecture to database
                        String note = "";
                        LectureHelperClass helperLecture = new LectureHelperClass(lName, note);
                        mBase.child(lName).setValue(helperLecture);
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
    public void setPresenter(LectureListContract.Presenter presenter) { mPresenter = presenter; }

    @Override
    public void setCourseName(String cName) { course = cName; }

    @Override
    public void setIsElevatedUser(Boolean isElevatedUser) {
        this.isElevatedUser = isElevatedUser;
    }

    @Override
    public void finishNoteActivity() {
        adapter.stopListening();
        getActivity().finish();
    }
}