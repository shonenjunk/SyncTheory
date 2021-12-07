package com.example.synctheory.CourseList;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;

import com.example.synctheory.LectureList.LectureListActivity;
import com.example.synctheory.R;
import com.example.synctheory.Data.course;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

// FirebaseRecyclerAdapter is a class provided by
// FirebaseUI. it provides functions to bind, adapt and show
// database contents in a Recycler View
public class courseAdapter extends FirebaseRecyclerAdapter<course, courseAdapter.coursesViewholder>{

    private Boolean isElevatedUser;

    public courseAdapter(@NonNull FirebaseRecyclerOptions<course> options, Boolean isElevatedUser)
    {
        super(options);
        this.isElevatedUser = isElevatedUser;
    }

    // Function to bind the view in Card view(here
    // "person.xml") with data in
    // model class(here "person.class")
    @Override
    protected void
    onBindViewHolder(@NonNull coursesViewholder holder, int position, @NonNull course model)
    {

        // Add courseName from model class (here
        // "course.class")to appropriate view in Card
        // view (here "course.xml")
        holder.courseName.setText(model.getCourseName());
        holder.courseName.setTag(model.getCourseName());
        holder.courseName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent lectureIntent = new Intent();
                String courseName = view.getTag().toString();
                lectureIntent.setClass(view.getContext(), LectureListActivity.class);
                lectureIntent.putExtra("CourseName", courseName);
                lectureIntent.putExtra("isElevatedUser", isElevatedUser);
                view.getContext().startActivity(lectureIntent);
            }
        });

    }

    // Function to tell the class about the Card view (here
    // "course.xml")in
    // which the data will be shown
    @NonNull
    @Override
    public coursesViewholder
    onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.course, parent, false);
        return new courseAdapter.coursesViewholder(view);
    }

    // Sub Class to create references of the views in Crad
    // view (here "person.xml")
    class coursesViewholder extends RecyclerView.ViewHolder {
        TextView courseName;
        public coursesViewholder(@NonNull View itemView)
        {
            super(itemView);
            courseName = itemView.findViewById(R.id.tvCourseName);
        }
    }

}
