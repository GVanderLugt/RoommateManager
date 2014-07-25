package edu.iupui.gdvander.roommatemanager.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import edu.iupui.gdvander.roommatemanager.handler.JsonObjectRequestHandler;


public class RegisterActivity extends Activity {

    private String url;
    private String username;
    private String email;
    private String password;
    private String confirmPassword;
    private int responseSuccess;
    private String responseMessage;
    private EditText usernameText;
    private EditText emailText;
    private EditText passwordText;
    private EditText confirmPasswordText;
    private JSONObject userInfo = new JSONObject();
    private JsonObjectRequestHandler requestHandler = new JsonObjectRequestHandler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Set the EditText variables
        usernameText = (EditText) findViewById(R.id.editTextUsername);
        emailText = (EditText) findViewById(R.id.editTextEmail);
        passwordText = (EditText) findViewById(R.id.editTextPassword);
        confirmPasswordText = (EditText) findViewById(R.id.editTextConfirmPassword);

        //Set the url variable
        //url = "http://192.168.0.10:9080/api/user/register/";
        url = "/api/user/register/";

        //Set btnCreateAccount onClickListener
        Button btnCreateAccount = (Button) findViewById(R.id.btnCreateAccount);
        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Get user entered data
                username = usernameText.getText().toString();
                email = emailText.getText().toString();
                password = passwordText.getText().toString();
                confirmPassword = confirmPasswordText.getText().toString();

                //If password and confirmPassword match, create the new account
                if(password.equals(confirmPassword)){

                    //Populate json object with user entered data
                    try{
                        userInfo.put("username", username);
                        userInfo.put("email", email);
                        userInfo.put("password", password);
                    }
                    catch(JSONException e){
                        Log.e("JSON Exception", e.toString());
                    }

                    //Set the response listener
                    Response.Listener responseListener = new Response.Listener<JSONObject>(){
                        @Override
                        public void onResponse(JSONObject response){
                            //Handle the json response
                            try{
                                responseMessage = response.getString("message");
                                responseSuccess = response.getInt("success");
                            }
                            catch(JSONException e){
                                //Log the exception
                                Log.e("JSON Exception", e.toString());

                                //Display a toast
                                Toast.makeText(getApplicationContext(),
                                        "Error retrieving server response.",
                                        Toast.LENGTH_SHORT).show();
                            }

                            //Display a toast
                            Toast.makeText(getApplicationContext(),
                                    responseMessage,
                                    Toast.LENGTH_SHORT).show();

                            if(responseSuccess == 1){
                                //Start the LoginActivity
                                Intent intentLogin = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(intentLogin);
                            }
                        }
                    };

                    //Set the error response listener
                    Response.ErrorListener responseErrorListener = new Response.ErrorListener(){
                        @Override
                        public void onErrorResponse(VolleyError error){
                            //Log the error
                            Log.e("Response Error", error.toString());

                            //Make a toast
                            Toast.makeText(getApplicationContext(),
                                    "Network Communication Error",
                                    Toast.LENGTH_SHORT).show();
                        }
                    };

                    //Use the request handler to send the Volley json POST request
                    requestHandler.post(url, userInfo, responseListener, responseErrorListener);


                    /*
                    //Send Volley json POST request including the json object
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                            Request.Method.POST, url, userInfo, new Response.Listener<JSONObject>(){
                        @Override
                        public void onResponse(JSONObject response){
                            //Handle the json response
                            try{
                                responseMessage = response.getString("message");
                                responseSuccess = response.getInt("success");
                            }
                            catch(JSONException e){
                                //Log the exception
                                Log.e("JSON Exception", e.toString());

                                //Display a toast
                                Toast.makeText(getApplicationContext(),
                                        "Error retrieving server response.",
                                        Toast.LENGTH_SHORT).show();
                            }

                            //Display a toast
                            Toast.makeText(getApplicationContext(),
                                    responseMessage,
                                    Toast.LENGTH_SHORT).show();

                            if(responseSuccess == 1){
                                //Start the LoginActivity
                                Intent intentLogin = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(intentLogin);
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //Log the error
                            Log.e("Response Error", error.toString());

                            //Make a toast
                            Toast.makeText(getApplicationContext(),
                                    "Network Communication Error",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }){
                        @Override
                        public Map<String,String> getHeaders() throws AuthFailureError {
                            Map<String,String> params = new HashMap<String,String>();
                            params.put("Content-Type","application/x-www-form-urlencoded");
                            return params;
                        }
                    };

                    //Access the RequestQueue through the singleton class
                    VolleySingleton.getInstance().addToRequestQueue(jsonObjectRequest);

                    */

                }
                else{
                    Toast.makeText(getApplicationContext(),
                            "Passwords do not match.",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.register, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
