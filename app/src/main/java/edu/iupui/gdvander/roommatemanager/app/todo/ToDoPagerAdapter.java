package edu.iupui.gdvander.roommatemanager.app.todo;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.Fragment;

public class ToDoPagerAdapter extends FragmentPagerAdapter {

    public ToDoPagerAdapter(FragmentManager fm) {super(fm);}

    @Override
    public Fragment getItem(int position){
        //GetItem is called to instantiate the fragment for the given page.
        switch(position){
            case 0:
                return new MyToDoTab();
            case 1:
                return new SharedToDoTab();
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
                return "My To - Do";
            case 1:
                return "Shared To - Do";
        }
        return null;
    }
}
