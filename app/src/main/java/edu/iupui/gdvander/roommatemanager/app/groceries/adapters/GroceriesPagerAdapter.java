package edu.iupui.gdvander.roommatemanager.app.groceries.adapters;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.Fragment;

import edu.iupui.gdvander.roommatemanager.app.groceries.tabs.PersonalListTab;
import edu.iupui.gdvander.roommatemanager.app.groceries.tabs.SharedListTab;
import edu.iupui.gdvander.roommatemanager.app.groceries.tabs.ShoppingListTab;
import edu.iupui.gdvander.roommatemanager.app.groceries.tabs.TestGroceryItemTabFragment;

public class GroceriesPagerAdapter extends FragmentPagerAdapter {

    public GroceriesPagerAdapter(FragmentManager fm) {super(fm);}

    @Override
    public Fragment getItem(int position){
        //GetItem is called to instantiate the fragment for the given page.
        switch(position){
            case 0:
                return new ShoppingListTab();
            case 1:
                return new PersonalListTab();
                //return new TestGroceryItemTabFragment();
            case 2:
                return new SharedListTab();
        }
        return null;
    }

    @Override
    public int getCount(){
        //Number of tabs to be displayed
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position){
        switch(position){
            case 0:
                return "Shopping List";
            case 1:
                return "Personal List";
            case 2:
                return "Shared List";
        }
        return null;
    }
}
