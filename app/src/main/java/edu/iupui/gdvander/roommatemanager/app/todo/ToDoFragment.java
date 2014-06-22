package edu.iupui.gdvander.roommatemanager.app.todo;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.iupui.gdvander.roommatemanager.app.R;

public class ToDoFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.fragment_to_do, container, false);

        return rootView;
    }
}