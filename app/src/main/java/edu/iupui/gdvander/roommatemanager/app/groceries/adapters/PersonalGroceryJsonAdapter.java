package edu.iupui.gdvander.roommatemanager.app.groceries.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

import edu.iupui.gdvander.roommatemanager.app.R;

public class PersonalGroceryJsonAdapter extends ArrayAdapter<String> {
    private final Context context;
    //private final String [] values;
    private ArrayList<String> values;

    public PersonalGroceryJsonAdapter(Context context, ArrayList<String> values) {
        super(context, R.layout.personal_list_item, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Get the layout inflater
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //Create a new row view and inflate it
        View rowView = inflater.inflate(R.layout.personal_list_item, parent, false);

        //Set the row's views
        TextView textView = (TextView) rowView.findViewById(R.id.textViewItemName);
        CheckBox checkBox = (CheckBox) rowView.findViewById(R.id.checkBoxItemStatus);

        //Add content to the views
        //textView.setText(values[position]);
        textView.setText(values.get(position));

        //Return the row view
        return rowView;
    }
}
