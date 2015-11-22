package com.kabam.kabam;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;

import com.facebook.FacebookSdk;
import com.layer.sdk.LayerClient;
import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseFacebookUtils;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class MainActivity extends FragmentActivity {

    public static HashMap<String, ParseUser> allUsers;

    public static void updateUsers(){
        ParseQuery<ParseUser> userQuery = ParseUser.getQuery();
        userQuery.findInBackground(new FindCallback<ParseUser>() {
            public void done(List<ParseUser> results, ParseException e) {
                if (e == null) {
                    allUsers = new HashMap<>();
                    for (int i = 0; i < results.size(); i++) {
                        allUsers.put(results.get(i).getObjectId(), results.get(i));
                    }
                }
            }
        });
    }

    public static String getUsername(String id){
        return MainActivity.allUsers.get(id).get("first_name") + " " + MainActivity.allUsers.get(id).get("last_name");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        // Initialize FacebookSDK
        FacebookSdk.sdkInitialize(getApplicationContext());

        // Initialize Parse
        ParseObject.registerSubclass(Class.class);
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "TnpAVtQJyj1fBngIFSKjRcWuMh3g8VwtWsjXw2sV", "oZZa2IMMaFQOgV20qLA84DkqWWCA8EpUDWZeUHV9");
        ParseFacebookUtils.initialize(getApplicationContext());

        updateUsers();

        // Show Login Page if User isn't logged in
        if (ParseUser.getCurrentUser() == null) {
            Intent loginIntent = new Intent(MainActivity.this, Login.class);
            MainActivity.this.startActivity(loginIntent);
        }

        // Control Back Button
        getSupportFragmentManager().addOnBackStackChangedListener(new android.support.v4.app.FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                getActionBar().setDisplayHomeAsUpEnabled(getSupportFragmentManager().getBackStackEntryCount() != 0);
            }
        });

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragmentContainer, new Classes());
        ft.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_profile:
                if (ParseUser.getCurrentUser() == null) {
                    Intent loginIntent = new Intent(MainActivity.this, Login.class);
                    MainActivity.this.startActivity(loginIntent);
                } else {
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.fragmentContainer, new Profile());
                    ft.addToBackStack("profile");
                    ft.commit();
                }
                return true;

            case R.id.action_search: //if search is clicked
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//                ft.replace(R.id.fragmentContainer, new Search());
//                ft.addToBackStack("search");
                //TESTED ADD_EVENT CODE HERE, CHANGE BACK TO WHATEVER
//                ft.replace(R.id.fragmentContainer, new AddEvent());
//                ft.addToBackStack("add_event");
                ft.replace(R.id.fragmentContainer, new Chat());
                ft.addToBackStack("chat_screen");
                ft.commit();
                return true;

            case android.R.id.home:
                getSupportFragmentManager().popBackStack();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
