package edu.iupui.gdvander.roommatemanager.adapter;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.Fragment;

import edu.iupui.gdvander.roommatemanager.app.home.HomeTab;

public class TabsPagerAdapter extends FragmentPagerAdapter {

    public TabsPagerAdapter(FragmentManager fm) {super(fm);}

    @Override
    public Fragment getItem(int position){
        //GetItem is called to instantiate the fragment for the given page.
        switch(position){
            case 0:
                return new HomeTab();
        }
        return null;
    }

    @Override
    public int getCount(){
        //Show 3 total pages.
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position){
        switch(position){
            case 0:
                return "Home";
        }
        return null;
    }
}
