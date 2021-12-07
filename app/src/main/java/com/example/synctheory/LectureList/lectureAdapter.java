package com.example.synctheory.LectureList;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;

import com.example.synctheory.LectureList.LectureListActivity;
import com.example.synctheory.Note.NoteActivity;
import com.example.synctheory.R;
import com.example.synctheory.Data.lecture;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
public class lectureAdapter extends FirebaseRecyclerAdapter<lecture, lectureAdapter.lecturesViewholder>{


    // FirebaseRecyclerAdapter is a class provided by
// FirebaseUI. it provides functions to bind, adapt and show
// database contents in a Recycler View
    private String courseName;

    public lectureAdapter(
            @NonNull FirebaseRecyclerOptions<lecture> options,
            String courseName)
    {
        super(options);
        this.courseName = courseName;
    }

    // Function to bind the view in Card view(here
    // "lecture.xml") with the data in
    // model class(here "lecture.class")
    @Override
    protected void
    onBindViewHolder(@NonNull lecturesViewholder holder,
                     int position, @NonNull lecture model)
    {

        // Add courseName from model class (here
        // "course.class")to appropriate view in Card
        // view (here "course.xml")
        holder.lectureName.setText(model.getLectureName());
        holder.lectureName.setTag(model.getLectureName());
        holder.lectureName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent noteIntent = new Intent();
                String lectureName = view.getTag().toString();
                noteIntent.setClass(view.getContext(), NoteActivity.class);
                noteIntent.putExtra("LectureName", lectureName);
                noteIntent.putExtra("CourseName", courseName);
                view.getContext().startActivity(noteIntent);

            }
        });

    }

    // Function to tell the class about the Card view (here
    // "course.xml")in
    // which the data will be shown
    @NonNull
    @Override
    public lecturesViewholder
    onCreateViewHolder(@NonNull ViewGroup parent,
                       int viewType)
    {
        View view
                = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lecture, parent, false);
        return new lecturesViewholder(view);
    }

    // Sub Class to create references of the views in Crad
    // view (here "lecture.xml")
    class lecturesViewholder
            extends RecyclerView.ViewHolder {
        TextView lectureName;
        public lecturesViewholder(@NonNull View itemView)
        {
            super(itemView);

            lectureName = itemView.findViewById(R.id.tvLectureName);

        }
    }


}

