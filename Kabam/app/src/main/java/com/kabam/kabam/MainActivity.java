package com.kabam.kabam;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize FacebookSDK
        FacebookSdk.sdkInitialize(getApplicationContext());

        // Initialize Parse
        ParseObject.registerSubclass(Class.class);
        ParseObject.registerSubclass(Post.class);
        ParseObject.registerSubclass(Event.class);

//        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "TnpAVtQJyj1fBngIFSKjRcWuMh3g8VwtWsjXw2sV", "oZZa2IMMaFQOgV20qLA84DkqWWCA8EpUDWZeUHV9");
        ParseFacebookUtils.initialize(getApplicationContext());

        ParseUtilities.updateAllUsers();
        ParseUtilities.updateAllClasses();

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

            case R.id.action_search:
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.fragmentContainer, new Search());
                ft.addToBackStack("search");
                ft.commit();
                //TESTED ADD_EVENT CODE HERE, CHANGE BACK TO WHATEVER

//                ClassDetail c = new ClassDetail();
//                Bundle bundle = new Bundle(2);
//                bundle.putString("title", "EE 209");
//                bundle.putString("enrolled", "18 Enrolled");
//                c.setArguments(bundle);
//                ft.replace(R.id.fragmentContainer, c);
//                //ft.replace(R.id.fragmentContainer, new AddEvent());
//                ft.addToBackStack("add_event");
//                ft.commit();
                return true;

            case android.R.id.home:
                getSupportFragmentManager().popBackStack();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
