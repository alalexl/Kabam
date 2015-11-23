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
        ((TextView) view.findViewById(R.id.name)).setText(ParseUtilities.getName(ParseUser.getCurrentUser().getObjectId()));

        view.findViewById(R.id.timeline).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.fragmentContainer, new Timeline());
                ft.addToBackStack("my timeline");
                ft.commit();
            }
        });

        view.findViewById(R.id.classes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.fragmentContainer, new MyClasses());
                ft.addToBackStack("my classes");
                ft.commit();
            }
        });

        return view;
    }
}
