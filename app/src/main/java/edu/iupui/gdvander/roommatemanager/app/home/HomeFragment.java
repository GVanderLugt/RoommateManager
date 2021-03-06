package edu.iupui.gdvander.roommatemanager.app.home;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.iupui.gdvander.roommatemanager.app.R;

public class HomeFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        //Initialize
        HomePagerAdapter pagerAdapter = new HomePagerAdapter(getChildFragmentManager());
        ViewPager viewPager = (ViewPager) getView().findViewById(R.id.pager);

        //Set the view pager's adapter
        viewPager.setAdapter(pagerAdapter);

        //Set the tab indicator color on the PagerTabStrip
        PagerTabStrip pagerTabStrip = (PagerTabStrip) getView().findViewById(R.id.pager_tab_strip);
        pagerTabStrip.setTabIndicatorColor(Color.parseColor(getString(R.string.tab_indicator_color)));
    }

}