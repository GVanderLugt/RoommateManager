package edu.iupui.gdvander.roommatemanager.adapter;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.Fragment;

import edu.iupui.gdvander.roommatemanager.app.home.DetailsTab;
import edu.iupui.gdvander.roommatemanager.app.home.HomeTab;

public class TabsPagerAdapter extends FragmentPagerAdapter {

    public TabsPagerAdapter(FragmentManager fm) {super(fm);}

    @Override
    public Fragment getItem(int position){
        //GetItem is called to instantiate the fragment for the given page.
        switch(position){
            case 0:
                return new HomeTab();
            case 1:
                return new DetailsTab();
        }
        return null;
    }

    @Override
    public int getCount(){
        //Number of tabs to be displayed
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position){
        switch(position){
            case 0:
                return "Home";
            case 1:
                return "Details";
        }
        return null;
    }
}
