package edu.iupui.gdvander.roommatemanager.app;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import edu.iupui.gdvander.roommatemanager.handler.JsonObjectRequestHandler;

public class SplashActivity extends Activity {

    private String url;
    private int userID;
    private String authToken;
    private String username;
    private int responseSuccess;
    private JSONObject userInfo = new JSONObject();
    private JsonObjectRequestHandler requestHandler = new JsonObjectRequestHandler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //Hide the action bar
        getActionBar().hide();

        //Check to see if the user is logged in, if not, go to login screen
        SharedPreferences sharedPref = getApplicationContext()
                .getSharedPreferences("roommatemanager.app.userinfo", MODE_PRIVATE);

        //Set the userID and authToken
        userID = sharedPref.getInt("userID", -1);
        authToken = sharedPref.getString("authToken", "");
        username = sharedPref.getString("username", "");

        //If the user ID or token are not found, go to LoginActivity
        if(userID == -1){
            loginScreen();
        }
        if(authToken.equals("")){
            loginScreen();
        }

        //Populate the json object
        try{
            userInfo.put("userID", userID);
            userInfo.put("authToken", authToken);
        }
        catch(JSONException e){
            //Log the Exception
            Log.i("JSON Exception checking for user info on splash screen.", e.toString());
        }

        //Set the url
        url = "/api/user/auth/";

        //Set the response listener
        Response.Listener responseListener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //Handle the json response
                try{
                    responseSuccess = response.getInt("success");
                }
                catch(JSONException e){
                    //Log the exception
                    Log.e("JSON Exception", e.toString());
                }

                //If the response is equal to 1, the user is logged in
                if(responseSuccess == 1){
                    //Display toast
                    Toast.makeText(getApplicationContext(),
                            ("Logged in as " + username),
                            Toast.LENGTH_SHORT).show();

                    //Start MainActivity
                    Intent intentMain = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intentMain);
                    finish();
                }
            }
        };

        //Set the error response listener
        Response.ErrorListener responseErrorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Log the error
                Log.e("Response Error", error.toString());

                //Start LoginActivity
                loginScreen();

            }
        };

        //Use the request handler to send the Volley json POST request
        requestHandler.post(url, userInfo, responseListener, responseErrorListener);

    }

    private void loginScreen(){
        //Start the LoginActivity
        Intent intentLogin = new Intent(SplashActivity.this, LoginActivity.class);
        startActivity(intentLogin);
        finish();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.splash, menu);
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
