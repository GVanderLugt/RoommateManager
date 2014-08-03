package edu.iupui.gdvander.roommatemanager.app.groceries.tabs;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import edu.iupui.gdvander.roommatemanager.app.R;
import edu.iupui.gdvander.roommatemanager.app.groceries.PersonalItemDialog;
import edu.iupui.gdvander.roommatemanager.app.groceries.adapters.PersonalGroceryJsonAdapter;

public class PersonalListTab extends ListFragment {

    public static final int PERSONAL_LIST_FRAGMENT = 1;
    //String[] values = new String[] {"Bread", "Eggs", "Chicken", "Milk", "Orange Juice", "Lindsey's Home!"};
    ArrayList<String> values = new ArrayList<String>();
    PersonalGroceryJsonAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Allow the fragment to add action bar items
        setHasOptionsMenu(true);

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_personal_list_tab, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //Set list item data
        //String[] values = new String[] {"Bread", "Eggs", "Chicken", "Milk", "Orange Juice", "Lindsey's Home!"};

        //Create the list adapter
        adapter = new PersonalGroceryJsonAdapter(getActivity(), values);

        //Set the list adapter
        setListAdapter(adapter);
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
        //Create a new dialog
        PersonalItemDialog dialog = new PersonalItemDialog();

        //Pass this fragment to the dialog
        dialog.setTargetFragment(this, PERSONAL_LIST_FRAGMENT);

        //Show the dialog
        dialog.show(getFragmentManager().beginTransaction(), "PERSONAL_LIST_DIALOG");
    }

    public void addItem(String itemName){
        //Add an item to the list
        //adapter.addItem(itemName);
        adapter.add(itemName);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        //This will execute after dialog close, retrieving data
        switch(requestCode){
            case PERSONAL_LIST_FRAGMENT:
                if(resultCode == Activity.RESULT_OK){
                    //Get the bundled extras from the given intent
                    Bundle bundle = data.getExtras();

                    //System.out.println("######################### ###################");
                    //System.out.println(bundle.get("itemName"));

                    //Add and item to the list
                    this.addItem(bundle.get("itemName").toString());
                }
                else if(resultCode == Activity.RESULT_CANCELED){
                    //Executed if cancel button pressed in dialog
                }
                break;
        }
    }

}
