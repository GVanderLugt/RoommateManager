package edu.iupui.gdvander.roommatemanager.app.finances;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.Fragment;

public class FinancesPagerAdapter extends FragmentPagerAdapter {

    public FinancesPagerAdapter(FragmentManager fm) {super(fm);}

    @Override
    public Fragment getItem(int position){
        //GetItem is called to instantiate the fragment for the given page.
        switch(position){
            case 0:
                return new PersonalTransactionTab();
            case 1:
                return new SharedTransactionTab();
            case 2:
                return new DebtsTab();
            case 3:
                return new ExpensesTab();
            case 4:
                return new TransferTab();
        }
        return null;
    }

    @Override
    public int getCount(){
        //Number of tabs to be displayed
        return 5;
    }

    @Override
    public CharSequence getPageTitle(int position){
        switch(position){
            case 0:
                return "Personal Transactions";
            case 1:
                return "Shared Transactions";
            case 2:
                return "Debts";
            case 3:
                return "Expenses";
            case 4:
                return "Transfer Money";
        }
        return null;
    }
}
