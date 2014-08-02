package edu.iupui.gdvander.roommatemanager.app;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.widget.DrawerLayout;
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

import edu.iupui.gdvander.roommatemanager.app.finances.FinancesFragment;
import edu.iupui.gdvander.roommatemanager.app.groceries.GroceriesFragment;
import edu.iupui.gdvander.roommatemanager.app.home.HomeFragment;
import edu.iupui.gdvander.roommatemanager.app.todo.ToDoFragment;
import edu.iupui.gdvander.roommatemanager.handler.JsonObjectRequestHandler;

public class MainActivity extends FragmentActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    private String url;
    private int userID;
    private String authToken;
    private JsonObjectRequestHandler requestHandler = new JsonObjectRequestHandler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        //Initialize fragment
        android.support.v4.app.Fragment fragment;

        //Initialize fragment tag
        String fragmentTag;

        //Declare the fragment manager
        FragmentManager fragmentManager = getSupportFragmentManager();

        //Set initial title
        mTitle = "Home";
        setTitle(mTitle);

        //Switch to the fragment selected
        switch(position){
            case 0:
                //Set the fragment tag
                fragmentTag = "HOME_FRAGMENT";

                //Set the fragment. Find with tag.
                fragment = fragmentManager.findFragmentByTag(fragmentTag);

                //If the fragment does not exist, create a new one and keep the tag
                if(fragment == null){
                    fragment = new HomeFragment();
                    mTitle = "Home";
                }
                break;

            case 1:
                fragmentTag = "GROCERIES_FRAGMENT";
                fragment = fragmentManager.findFragmentByTag(fragmentTag);
                if(fragment == null){
                    fragment = new GroceriesFragment();
                    mTitle = "Groceries";
                }
                break;

            case 2:
                fragmentTag = "FINANCES_FRAGMENT";
                fragment = fragmentManager.findFragmentByTag(fragmentTag);
                if(fragment == null){
                    fragment = new FinancesFragment();
                    mTitle = "Finances";
                }
                break;

            case 3:
                fragmentTag = "TO_DO_FRAGMENT";
                fragment = fragmentManager.findFragmentByTag(fragmentTag);
                if(fragment == null){
                    fragment = new ToDoFragment();
                    mTitle = "To - Do";
                }
                break;

            default:
                //Set the initial fragment to the HomeFragment
                fragment = new HomeFragment();

                //Set the initial title to 'Home'
                mTitle = "Home";
                setTitle(mTitle);

                //Set the Home fragment tag
                fragmentTag = ("HOME_FRAGMENT");
        }

        //Replace the existing fragment with the new fragment
        fragmentManager.beginTransaction().replace(R.id.container, fragment, fragmentTag).commit();

    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
            case 4:
                mTitle = getString(R.string.title_section4);
                break;
        }
    }

    public void logout(){
        //Log the user out of the system. Throw away auth tokens and user info
        SharedPreferences sharedPref = getApplicationContext()
                .getSharedPreferences("roommatemanager.app.userinfo", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        //Get the user ID and auth token
        userID = sharedPref.getInt("userID", -1);
        authToken = sharedPref.getString("authToken", "");

        //Remove user info from shared preferences
        editor.remove("userID");
        editor.remove("username");
        editor.remove("authToken");
        editor.commit();

        //Create json object with user info
        JSONObject userInfo = new JSONObject();
        try{
            userInfo.put("userID", userID);
            userInfo.put("authToken", authToken);
        }
        catch(JSONException e){
            Log.e("JSON Exception", e.toString());
        }

        //Set the url
        url = "/api/user/logout/";

        //Set the response listener
        Response.Listener responseListener = new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response){
                //Handle the json response
                String responseMessage = "";
                try{
                    responseMessage = response.getString("message");
                }
                catch(JSONException e){
                    Log.e("JSON Exception", e.toString());
                }

                Log.i("Response Message", responseMessage);

                //Make a toast
                Toast.makeText(getApplicationContext(),
                        "Successfully logged out.",
                        Toast.LENGTH_SHORT).show();

                //Start the LoginActivity
                Intent intentLogin = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intentLogin);
            }
        };

        //Set the error response listener
        Response.ErrorListener responseErrorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Log the error
                Log.e("Response Error", error.toString());

                //Make a toast
                Toast.makeText(getApplicationContext(),
                        "Error logging out.",
                        Toast.LENGTH_SHORT).show();

                //Start the LoginActivity
                Intent intentLogin = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intentLogin);

            }
        };

        //Use the request handler to send the Volley json POST request
        requestHandler.post(url, userInfo, responseListener, responseErrorListener);
    }

    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
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
        if(id == R.id.action_logout){
            logout();
        }
        return super.onOptionsItemSelected(item);
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(String id);
    }
}
