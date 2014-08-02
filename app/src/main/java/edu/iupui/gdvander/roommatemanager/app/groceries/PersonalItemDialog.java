package edu.iupui.gdvander.roommatemanager.app.groceries;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.widget.EditText;

import edu.iupui.gdvander.roommatemanager.app.R;

public class PersonalItemDialog extends DialogFragment {

    EditText itemNameText;
    String itemName;
    int itemStatus = 0;

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

                //Add the item

            }});
        builder.setNegativeButton(R.string.title_grocery_negative, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //Cancel the dialog
                PersonalItemDialog.this.getDialog().cancel();
            }
        });

        //Create the AlertDialog object and return it
        return builder.create();
    }
}
