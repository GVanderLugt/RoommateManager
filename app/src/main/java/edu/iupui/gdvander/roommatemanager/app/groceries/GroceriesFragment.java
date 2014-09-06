package edu.iupui.gdvander.roommatemanager.app.groceries;

/**
 * Created by Gerrit VanderLugt.
 * Title: GroceriesFragment.java
 * Purpose: ListFragment that will display all of the grocery items belonging to the user's
 *  household.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import edu.iupui.gdvander.roommatemanager.app.R;

public class GroceriesFragment extends ListFragment {

    public static final int GROCERY_FRAGMENT = 1;
    ArrayList<GroceryItem> values = new ArrayList<GroceryItem>();
    GroceryArrayAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        //Allow the fragment to add action bar items
        setHasOptionsMenu(true);

        //Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_groceries, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        //Create the list adapter
        adapter = new GroceryArrayAdapter(getActivity(), values);

        //Set the list adapter
        setListAdapter(adapter);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        inflater.inflate(R.menu.grocery, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        //handle item selection
        switch(item.getItemId()){
            case R.id.action_add:
                this.newItemDialog();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void newItemDialog(){
        //Create a new dialog
        GroceryItemDialog dialog = new GroceryItemDialog();

        //Pass this fragment to the dialog
        dialog.setTargetFragment(this, GROCERY_FRAGMENT);

        //Show the dialog
        dialog.show(getFragmentManager().beginTransaction(), "GROCERY_FRAGMENT");
    }

    public void addItem(String itemName){
        //Add new GroceryItem to the list
        GroceryItem groceryItem = new GroceryItem(1, itemName, 0, 1);
        adapter.add(groceryItem);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        switch(requestCode){
            case GROCERY_FRAGMENT:
                if(resultCode == Activity.RESULT_OK){
                    //Get the bundled extras from the given intent
                    Bundle bundle = data.getExtras();

                    //Add an item to the list, pass item name to addItem
                    this.addItem(bundle.get("itemName").toString());
                }
                else if(resultCode == Activity.RESULT_CANCELED){
                    //Executed if cancel button pressed in dialog
                }
        }
    }
}