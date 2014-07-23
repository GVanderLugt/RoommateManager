package edu.iupui.gdvander.roommatemanager.app;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

public class VolleySingleton extends Application {

    public static final String TAG = VolleySingleton.class.getSimpleName();

    private RequestQueue mRequestQueue;
    //private ImageLoader mImageLoader;
    //private static Context mCtx;

    private static VolleySingleton mInstance;

    @Override
    public void onCreate(){
        super.onCreate();
        mInstance = this;
    }

    //private VolleySingleton(Context context){
        //mCtx = context;
        //mRequestQueue = getRequestQueue();
    //}

    public static synchronized VolleySingleton getInstance(){
        return mInstance;
    }

    public RequestQueue getRequestQueue(){
        if(mRequestQueue == null){
            //getApplicationContext() is key, it keeps you from leaking the
            //Activity or BrodcastReceiver if someone passes one in.
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }
}
