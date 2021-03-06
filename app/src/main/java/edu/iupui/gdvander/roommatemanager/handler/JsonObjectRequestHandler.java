package edu.iupui.gdvander.roommatemanager.handler;

/**
 * Created by Gerrit VanderLugt
 * Title: JsonObjectRequestHandler.java
 * Purpose: Handle http requests using Volley's JSONObjectRequest.
 */

import org.json.JSONObject;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;

import java.util.HashMap;
import java.util.Map;

import edu.iupui.gdvander.roommatemanager.app.VolleySingleton;

public class JsonObjectRequestHandler {

    //Declare the base url to be used. Should be where the API is hosted.  In this case, localhost.
    private String baseURL = "http://192.168.0.10:9080";

    //Initialize the url variable. This will be added to the base URL and is specified by the
    //class implementing the request.
    private String url;

    public void post(String path,
                     JSONObject jsonRequest,
                     Response.Listener<JSONObject> listener,
                     Response.ErrorListener errorListener){

        //Construct the url
        url = baseURL + path;

        //Send Volley json POST request including the given json object and listeners
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST, url, jsonRequest, listener, errorListener){
            @Override
            public Map<String,String> getHeaders() throws AuthFailureError {
                Map<String,String> params = new HashMap<String,String>();
                params.put("Content-Type","application/x-www-form-urlencoded");
                return params;
            }
        };

        //Access the RequestQueue through the singleton class
        VolleySingleton.getInstance().addToRequestQueue(jsonObjectRequest);

    }

    public void get(String path,
                    Response.Listener<JSONObject> listener,
                    Response.ErrorListener errorListener){
        //Construct the url
        url = (baseURL + path);

        //Send Volley json GET request including the given json object and listeners
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, url, null, listener, errorListener){
        };

        //Access the RequestQueue through the singleton class
        VolleySingleton.getInstance().addToRequestQueue(jsonObjectRequest);
    }

}
