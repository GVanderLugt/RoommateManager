package edu.iupui.gdvander.roommatemanager.app.finances;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.iupui.gdvander.roommatemanager.app.R;

public class PersonalTransactionTab extends Fragment {


    public PersonalTransactionTab() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_personal_transaction_tab, container, false);
    }


}