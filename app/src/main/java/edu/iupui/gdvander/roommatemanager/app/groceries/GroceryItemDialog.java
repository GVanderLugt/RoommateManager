package edu.iupui.gdvander.roommatemanager.app.groceries;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.widget.EditText;

import edu.iupui.gdvander.roommatemanager.app.R;

/**
 * Created by Gerrit on 9/1/2014.
 * Title: GroceryItemDialog
 * Purpose: A dialog used to get user input from the user to create a new grocery list item.
 */
public class GroceryItemDialog extends DialogFragment {
    EditText itemNameText;
    String itemName;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        //Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        //Set the dialog layout view
        builder.setView(inflater.inflate(R.layout.dialog_grocery_item, null));

        //Add action buttons
        builder.setPositiveButton(R.string.title_grocery_positive, new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int id){
                //Positive button clicked
                //Get the dialog
                Dialog d = (Dialog) dialog;

                //Set the EditText view
                itemNameText = (EditText) d.findViewById(R.id.editTextGroceryItemDialog);

                //Get the item name from the EditText
                itemName = itemNameText.getText().toString();

                //Create a Bundle and add data from EditText
                Bundle bundle = new Bundle();
                bundle.putString("itemName", itemName);

                //Add the bundle to the intent
                Intent intent = getActivity().getIntent();
                intent.putExtras(bundle);

                //Call onActivityResult from the target fragment and pass the request code/intent
                getTargetFragment().onActivityResult(
                        getTargetRequestCode(), Activity.RESULT_OK, intent
                );
            }});
        builder.setNegativeButton(R.string.title_grocery_negative, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //Cancel the dialog
                getTargetFragment().onActivityResult(
                        getTargetRequestCode(), Activity.RESULT_CANCELED, getActivity().getIntent()
                );
            }
        });

        //Create the AlertDialog object and return it
        return builder.create();
    }
}
