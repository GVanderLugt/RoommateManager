package edu.iupui.gdvander.roommatemanager.handler;

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

        layout.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent ev){
                hideKeyboard(view);
                return false;
            }
        });
    }

    private void hideKeyboard(View view){
        InputMethodManager in = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        in.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }
}
