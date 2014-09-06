package edu.iupui.gdvander.roommatemanager.app.groceries;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

import edu.iupui.gdvander.roommatemanager.app.R;

/**
 * Created by Gerrit VanderLugt on 9/6/2014.
 * Title: GroceryArrayAdapter.java
 * Purpose: GroceryItem ArrayAdapter to be used with GroceriesFragment. Uses ViewHolder Pattern.
 */
public class GroceryArrayAdapter extends ArrayAdapter<GroceryItem> {
    private final Context context;

    //ViewHolder
    GroceryViewHolder viewHolder;

    private ArrayList<GroceryItem> values;

    //Constructor when using ArrayList<String>
    public GroceryArrayAdapter(Context context, ArrayList<GroceryItem> values) {
        super(context, R.layout.grocery_list_item, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        //Use ViewHolder Pattern to populate the list and inflate the views
        //This limits the use of findViewById
        if(convertView == null){
            //Get the layout inflater
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            //Create a new row view and inflate it
            convertView = inflater.inflate(R.layout.grocery_list_item, parent, false);

            //Create the new ViewHolder
            viewHolder = new GroceryViewHolder();

            //Set the row's views in the ViewHolder
            viewHolder.textView = (TextView) convertView.findViewById(R.id.textViewItemName);
            viewHolder.checkBox = (CheckBox) convertView.findViewById(R.id.checkBoxItemStatus);

            //Store the ViewHolder with the view
            convertView.setTag(viewHolder);
        }
        else{
            //Get the ViewHolder by the convertView's tag
            viewHolder = (GroceryViewHolder) convertView.getTag();
        }

        //Get grocery object from position
        GroceryItem groceryItem = values.get(position);

        //Assign the ViewHolder's values if the object is not null
        if(groceryItem != null){
            viewHolder.textView.setText(groceryItem.getName());
            //Set checkbox checked here
        }

        return convertView;
    }
}

class GroceryViewHolder {
    TextView textView;
    CheckBox checkBox;
}

