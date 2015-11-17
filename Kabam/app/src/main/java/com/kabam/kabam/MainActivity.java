package com.kabam.kabam;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.facebook.FacebookSdk;
import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseUser;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Parse
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "TnpAVtQJyj1fBngIFSKjRcWuMh3g8VwtWsjXw2sV", "oZZa2IMMaFQOgV20qLA84DkqWWCA8EpUDWZeUHV9");

        // Initialize FacebookSDK
        FacebookSdk.sdkInitialize(getApplicationContext());

        // Show Login Page if User isn't logged in
        if (ParseUser.getCurrentUser() == null) {
            Intent loginIntent = new Intent(MainActivity.this, Login.class);
            MainActivity.this.startActivity(loginIntent);
        }
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
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
