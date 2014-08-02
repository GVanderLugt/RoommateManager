package edu.iupui.gdvander.roommatemanager.app.groceries.tabs;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import edu.iupui.gdvander.roommatemanager.app.groceries.tabs.dummy.DummyContent;

public class TestGroceryItemTabFragment extends ListFragment {

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setListAdapter(new ArrayAdapter<DummyContent.DummyItem>(getActivity(),
                android.R.layout.simple_list_item_1, android.R.id.text1, DummyContent.ITEMS));
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id){
        //Make a toast
        Toast.makeText(getActivity(),
                "Item " + (position + 1) + " selected.",
                Toast.LENGTH_SHORT).show();
    }
}
