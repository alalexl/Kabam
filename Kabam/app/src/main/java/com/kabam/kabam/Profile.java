package com.kabam.kabam;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.ParseUser;

/**
 * Created by Ayush on 11/16/15.
 */
//(KEVIN) ADDED ACTIONLISTENER TO TIMELINE BUTTON TO OPEN ADD_EVENT PAGE
public class Profile extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile, container, false);
        view.findViewById(R.id.logOut).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser.logOut();
                Log.d("Log Out", "Logging out.");
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
        view.findViewById(R.id.timeline).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.fragmentContainer, new AddEvent());
                ft.addToBackStack("add_event");
                ft.commit();
            }
        });
        ((TextView) view.findViewById(R.id.name)).setText(ParseUser.getCurrentUser().get("first_name") + " " + ParseUser.getCurrentUser().get("last_name"));
        return view;
    }
}
