package edu.iupui.gdvander.roommatemanager.app;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import edu.iupui.gdvander.roommatemanager.handler.JsonObjectRequestHandler;

public class LoginActivity extends Activity {

    private String url;
    private String username;
    private String password;
    private String responseMessage;
    private String responseAuthToken;
    private String responseUsername;
    private int responseSuccess;
    private int responseUserID;
    private EditText usernameText;
    private EditText passwordText;
    private JSONObject userInfo = new JSONObject();
    private JsonObjectRequestHandler requestHandler = new JsonObjectRequestHandler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Set the EditText variables
        usernameText = (EditText) findViewById(R.id.editTextUsername);
        passwordText = (EditText) findViewById(R.id.editTextPassword);

        //Set the url variable
        url = "/api/user/login/";

        //Set btnLogin listener
        Button btnLogin = (Button)findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Get user entered data
                username = usernameText.getText().toString();
                password = passwordText.getText().toString();

                //populate json object with user entered data
                try {
                    userInfo.put("username", username);
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
                            responseUserID = response.getInt("userID");
                            responseAuthToken = response.getString("authToken");
                            responseUsername = response.getString("username");

                        }
                        catch(JSONException e){
                            //Log the exception
                            Log.e("JSON Exception", e.toString());

                        }

                        //Write the username, user ID, and authenticated login token to shared preferences
                        SharedPreferences sharedPref = getApplicationContext()
                                .getSharedPreferences("roommatemanager.app.userinfo", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.putInt("userID", responseUserID);
                        editor.putString("username", responseUsername);
                        editor.putString("authToken", responseAuthToken);
                        editor.commit();

                        //Display a toast
                        Toast.makeText(getApplicationContext(),
                                responseMessage,
                                Toast.LENGTH_SHORT).show();

                        //If login successful, move to main app
                        if(responseSuccess == 1){
                            //Start the main activity
                            Intent intentMain = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intentMain);
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
            }

        });

        //set btnCreateAccount onClickListener
        Button btnCreateAccount = (Button)findViewById(R.id.btnCreateAccount);
        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Start the register activity
                Intent intentRegister = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intentRegister);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login, menu);
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
