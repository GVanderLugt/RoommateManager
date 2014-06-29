package edu.iupui.gdvander.roommatemanager.app.groceries;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import edu.iupui.gdvander.roommatemanager.app.MainActivity;
import edu.iupui.gdvander.roommatemanager.app.R;

public class PersonalListTab extends Fragment {


    public PersonalListTab() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_personal_list_tab, container, false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        //Declare the the fragment has menu items
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        inflater.inflate(R.menu.personal_list_tab, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        //handle item selection
        switch(item.getItemId()){
            case R.id.action_example:
                //Get application context
                Context context = getActivity().getApplicationContext();

                //Display toast
                Toast.makeText(context, "Personal List Tab!", Toast.LENGTH_SHORT).show();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
