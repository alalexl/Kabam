package com.kabam.kabam;

import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.parse.LogInCallback;
import com.parse.ParseFacebookUtils;
import com.parse.ParseUser;
import com.parse.ParseException;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Ayush on 11/16/15.
 */
public class Login extends FragmentActivity {

    private Dialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
    }

    public void onLoginClick(View view) {
        progressDialog = ProgressDialog.show(Login.this, "", "Logging In...", true);

        String email = ((EditText) findViewById(R.id.email)).getText().toString();
        String password = ((EditText) findViewById(R.id.password)).getText().toString();
        ParseUser.logInInBackground(email, password, new LogInCallback() {
            public void done(ParseUser user, ParseException e) {
                progressDialog.dismiss();
                if (user == null) {
                    Toast.makeText(getApplicationContext(), "Blah Can't Log In", Toast.LENGTH_SHORT).show();
                } else {
                    finishLogin();
                    Toast.makeText(getApplicationContext(), "Blah Logged In", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void onFacebookLoginClick(View view) {
        progressDialog = ProgressDialog.show(Login.this, "", "Logging In...", true);

        List<String> permissions = Arrays.asList("public_profile", "email");
        ParseFacebookUtils.logInWithReadPermissionsInBackground(this, permissions, new LogInCallback() {
            public void done(ParseUser user, ParseException err) {
                progressDialog.dismiss();
                if (user == null) {
                    Log.d("FB_L", "Unable to log in");
                } else if (user.isNew()) {
                    Log.d("FB_L", "Logged in as new user");
                    finishLogin();
                } else {
                    Log.d("FB_L", "Logged in as old user");
                    finishLogin();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ParseFacebookUtils.onActivityResult(requestCode, resultCode, data);
    }

    private void finishLogin() {
        finish();
    }
}
