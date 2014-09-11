package edu.iupui.gdvander.roommatemanager.app;

/**
 * Created by Gerrit VanderLugt.
 * Title: VolleySingleton.java
 * Purpose: Handle the Volley RequestQueue. It is important that we use a singleton because we
 *   only need a single instance of Volley. Volley handles all http requests, synchronization, and
 *   threading on its own. If we have multiple instances, it cannot synchronize requests if they
 *   are in different queues.
 */

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleySingleton extends Application {

    public static final String TAG = VolleySingleton.class.getSimpleName();

    //Initialize the RequestQueue
    private RequestQueue mRequestQueue;

    //Initialize the instance of VolleySingleton
    private static VolleySingleton mInstance;

    @Override
    public void onCreate(){
        super.onCreate();
        //Set the VolleySingleton instance to itself
        mInstance = this;
    }

    public static synchronized VolleySingleton getInstance(){
        //Return the VolleySingleton instance (itself)
        return mInstance;
    }

    public RequestQueue getRequestQueue(){
        if(mRequestQueue == null){
            //If the request queue has not yet been created in the application lifecycle,
            //create a new one.
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        //Return the requestQueue
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        /**
         * This method would be used if you wanted to track your http request. You would assign
         * the request a tag. This would allow you to cancel the request, among other things, if
         * the request has not yet been sent and it is still waiting in the queue.
         */

        // set the tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);

        //Add the request to the request queue
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        //Set the request tag to default tag
        req.setTag(TAG);

        //Add the request to the request queue
        getRequestQueue().add(req);
    }
}
