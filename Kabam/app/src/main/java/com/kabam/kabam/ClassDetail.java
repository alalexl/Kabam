package com.kabam.kabam;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Set;

/**
 * Created by Alex on 11/19/15.
 */
public class ClassDetail extends Fragment {

    private Class selectedClass;
    private EventQueryAdapter classEvents;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.class_detail, container, false);

        if (getArguments() != null) {
            String classID = getArguments().getString("class");
            selectedClass = ParseUtilities.getClass(classID);
        } else {
            getActivity().getSupportFragmentManager().popBackStack();
        }

        if (selectedClass != null) {
            ((TextView)view.findViewById(R.id.className)).setText(selectedClass.getClassTitle());
            ((TextView)view.findViewById(R.id.classEnrolled)).setText(selectedClass.getEnrollCount());
            classEvents = new EventQueryAdapter(getActivity(), selectedClass);
            ((ListView)view.findViewById(R.id.classDetailList)).setAdapter(classEvents);
        }

        view.findViewById(R.id.eventButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (classEvents != null) {
                    ((ListView)getView().findViewById(R.id.classDetailList)).setAdapter(classEvents);
                }
            }
        });

        view.findViewById(R.id.addEventButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedClass != null) {
                    String selectedClassObjectID = selectedClass.getObjectId();
                    FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                    Bundle bundle = new Bundle();
                    bundle.putString("class", selectedClassObjectID);
                    AddEvent addEvent = new AddEvent();
                    addEvent.setArguments(bundle);
                    ft.replace(R.id.fragmentContainer, addEvent);
                    ft.addToBackStack("add event");
                    ft.commit();
                }
            }
        });

        view.findViewById(R.id.chatButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                Chat chat = new Chat();
                ft.replace(R.id.fragmentContainer, chat);
                ft.addToBackStack("add chat");
                ft.commit();
            }
        });

        view.findViewById(R.id.addChatButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedClass != null) {
                    String selectedClassObjectID = selectedClass.getObjectId();
                    FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                    Bundle bundle = new Bundle();
                    bundle.putString("class", selectedClassObjectID);
                    Message message = new Message();
                    message.setArguments(bundle);
                    ft.replace(R.id.fragmentContainer, message);
                    ft.addToBackStack("add chat");
                    ft.commit();
                }
            }
        });

        return view;
    }
}
