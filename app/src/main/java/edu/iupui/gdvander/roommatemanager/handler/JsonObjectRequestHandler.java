package edu.iupui.gdvander.roommatemanager.handler;

import org.json.JSONObject;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;

import java.util.HashMap;
import java.util.Map;

import edu.iupui.gdvander.roommatemanager.app.VolleySingleton;

public class JsonObjectRequestHandler {

    private String baseURL = "http://192.168.0.10:9080";
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
