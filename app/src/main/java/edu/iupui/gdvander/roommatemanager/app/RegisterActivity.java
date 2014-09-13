package edu.iupui.gdvander.roommatemanager.app;

/**
 * Created by Gerrit VanderLugt.
 * Title: RegisterActivity.java
 * Purpose: Register a new user in the system.
 */

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import edu.iupui.gdvander.roommatemanager.handler.JsonObjectRequestHandler;
import edu.iupui.gdvander.roommatemanager.handler.KeyboardHandler;

public class RegisterActivity extends Activity {

    private String url;
    private String username;
    private String email;
    private String password;
    private String confirmPassword;
    private String houseName;
    private String housePassword;
    private String checked = "New House";
    private int responseSuccess;
    private String responseMessage;
    private EditText usernameText;
    private EditText emailText;
    private EditText passwordText;
    private EditText confirmPasswordText;
    private EditText houseNameText;
    private EditText housePasswordText;
    private RadioGroup radioButtonGroup;
    private JSONObject userInfo = new JSONObject();
    private JsonObjectRequestHandler requestHandler = new JsonObjectRequestHandler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Hide the keyboard when the view behind it is selected
        new KeyboardHandler(
                //Pass the view to the KeyboardHandler
                (RelativeLayout) findViewById(R.id.register_layout), getApplicationContext()
        );

        //Set the EditText member variables
        usernameText = (EditText) findViewById(R.id.editTextUsername);
        emailText = (EditText) findViewById(R.id.editTextEmail);
        passwordText = (EditText) findViewById(R.id.editTextPassword);
        confirmPasswordText = (EditText) findViewById(R.id.editTextConfirmPassword);
        houseNameText = (EditText) findViewById(R.id.editTextHouseName);
        housePasswordText = (EditText) findViewById(R.id.editTextHousePassword);

        //Set the RadioGroup member variable
        radioButtonGroup = (RadioGroup) findViewById(R.id.radioGroup);

        //Set the fonts of the editTexts
        usernameText.setTypeface(Typeface.DEFAULT);
        emailText.setTypeface(Typeface.DEFAULT);
        passwordText.setTypeface(Typeface.DEFAULT);
        confirmPasswordText.setTypeface(Typeface.DEFAULT);
        passwordText.setTransformationMethod(new PasswordTransformationMethod());
        confirmPasswordText.setTransformationMethod(new PasswordTransformationMethod());
        houseNameText.setTypeface(Typeface.DEFAULT);
        housePasswordText.setTypeface(Typeface.DEFAULT);
        housePasswordText.setTransformationMethod(new PasswordTransformationMethod());

        //Set the url member variable
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
                houseName = houseNameText.getText().toString();
                housePassword = housePasswordText.getText().toString();

                //Check to see which radio button is selected
                //Get the ID of the view of the radio button that is checked
                int radioButtonID = radioButtonGroup.getCheckedRadioButtonId();

                //Get the radio button view by the ID
                View radioButtonView = radioButtonGroup.findViewById(radioButtonID);

                //Find the index (in the radioGroup) of the view
                switch(radioButtonGroup.indexOfChild(radioButtonView)){
                    case 0:
                        checked = "New House";
                        break;
                    case 1:
                        checked = "Join Existing";
                        break;
                }

                //If password and confirmPassword match, create the new account
                if(password.equals(confirmPassword)){

                    //Populate json object with user entered data
                    try{
                        userInfo.put("username", username);
                        userInfo.put("email", email);
                        userInfo.put("password", password);
                        userInfo.put("houseName", houseName);
                        userInfo.put("housePassword", housePassword);
                        userInfo.put("houseStatus", checked);
                    }
                    catch(JSONException e){
                        //Log the exception
                        Log.e("JSON Exception", e.toString());
                    }

                    //Set the response listener
                    Response.Listener responseListener = new Response.Listener<JSONObject>(){
                        @Override
                        public void onResponse(JSONObject response){
                            //Handle the json response
                            try{
                                //Set variables with json response
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
                                    Toast.LENGTH_LONG).show();

                            if(responseSuccess == 1){
                                //Start the LoginActivity
                                Intent intentLogin = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(intentLogin);
                                finish();
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
                else{
                    //Make a toast
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
