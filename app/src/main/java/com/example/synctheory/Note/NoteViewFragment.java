package com.example.synctheory.Note;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.InputType;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.example.synctheory.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class NoteViewFragment extends Fragment implements NoteContract.View{

    private NoteContract.Presenter mPresenter;
    //edit text that represents the lecture note
    EditText etNote;

    // course and lecture name thats defined from the lecture activities intent extra values
    String course;
    String lectureName;

    public NoteViewFragment() {
        // Required empty public constructor
    }


    public static NoteViewFragment newInstance() {
        NoteViewFragment fragment = new NoteViewFragment();
        return fragment;
    }

    @Override
    public void setPresenter(NoteContract.Presenter presenter) {
         mPresenter = presenter;
    }

    //allows the lecture activity to pass in the correct course and lecture name to retrive the proper note
    public void setCourseName(String cName){
        course = cName;
    }
    public void setLectureName(String lName){
        lectureName = lName;
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
        View root =  inflater.inflate(R.layout.fragment_note_view, container, false);

        //assigns the note to the edit text
        etNote = root.findViewById(R.id.editTextNote);
        etNote.setImeOptions(EditorInfo.IME_ACTION_DONE);
        etNote.setRawInputType(InputType.TYPE_CLASS_TEXT);
        String note = etNote.getText().toString();

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();


        //retrieves the reference to the specific note from the passed in course and lectureName from the intent extras.
        DatabaseReference myRef = database.getReference(course).child(lectureName).child("note");


        //edit text on enter key pressed listener, update the current note in firebase, unfocus keyboard, etc.
        etNote.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if(i == EditorInfo.IME_ACTION_DONE){
                    //update the current note, close out of the keyboard
                    myRef.setValue(etNote.getText().toString());
                    //InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    InputMethodManager im = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    im.hideSoftInputFromWindow(etNote.getWindowToken(), 0);
                }
                return false;
            }
        });

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                etNote.setText(value);
                Log.d("TAG", "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("TAG", "Failed to read value.", error.toException());
            }
        });

        return root;
    }


    @Override
    public void onResume(){ super.onResume(); }

    //function to end the note activity when the user hits the back button on the phone
    @Override
    public void finishNoteActivity() { getActivity().finish(); }

}