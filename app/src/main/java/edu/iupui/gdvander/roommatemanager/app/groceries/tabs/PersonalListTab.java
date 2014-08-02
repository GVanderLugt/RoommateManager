package edu.iupui.gdvander.roommatemanager.app.groceries.tabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import edu.iupui.gdvander.roommatemanager.app.R;
import edu.iupui.gdvander.roommatemanager.app.groceries.PersonalItemDialog;

public class PersonalListTab extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Allow the fragment to add action bar items
        setHasOptionsMenu(true);

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_personal_list_tab, container, false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        inflater.inflate(R.menu.personal_list_tab, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        //handle item selection
        switch(item.getItemId()){
            case R.id.action_add:
                this.displayDialog();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void displayDialog(){
        //Display dialog
        PersonalItemDialog dialog = new PersonalItemDialog();
        dialog.show(getFragmentManager(), "PERSONAL_LIST_DIALOG");
    }

}
