package edu.iupui.gdvander.roommatemanager.handler;

/**
 * Created by Gerrit VanderLugt
 * Title: KeyboardHandler.java
 * Purpose: Hide the keyboard when the view behind the keyboard is pressed. Create an instance
 *   of this class and pass the view using the keyboard in the constructor to implement.
 */

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.RelativeLayout;

public class KeyboardHandler {

    private Context context;

    public KeyboardHandler(RelativeLayout layout, Context applicationContext){
        //Set the context
        context = applicationContext;

        //Essentially, turn the view behind the keyboard into a button by giving it an
        //onTouchListener.
        layout.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent ev){
                //Hide the keyboard on button press.
                hideKeyboard(view);
                return false;
            }
        });
    }

    private void hideKeyboard(View view){
        //Hide the keyboard
        InputMethodManager in = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        in.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }
}
