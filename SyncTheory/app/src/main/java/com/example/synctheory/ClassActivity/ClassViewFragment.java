package com.example.synctheory.ClassActivity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.synctheory.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ClassViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ClassViewFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match


    // TODO: Rename and change types of parameters

    public ClassViewFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ClassViewFragment newInstance() {
        ClassViewFragment fragment = new ClassViewFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_class_view, container, false);
    }
}