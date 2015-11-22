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

import com.parse.ParseUser;

import java.util.List;
import java.util.Set;

/**
 * Created by Alex on 11/19/15.
 */
public class ClassDetail extends Fragment {

    private Class selectedClass;
    private EventQueryAdapter classEvents;
    private Boolean enrolled = false;
    private ListView classDetailList;
    private View enrollButton, eventButton, addEventButton, chatButton, addChatButton;

    private void notLoggedIn() {
        enrollButton.setVisibility(View.GONE);
        eventButton.setVisibility(View.GONE);
        addEventButton.setVisibility(View.GONE);
        chatButton.setVisibility(View.GONE);
        addChatButton.setVisibility(View.GONE);
        classDetailList.setVisibility(View.GONE);
    }

    private void notEnrolled() {
        enrollButton.setVisibility(View.VISIBLE);
        eventButton.setVisibility(View.GONE);
        addEventButton.setVisibility(View.GONE);
        chatButton.setVisibility(View.GONE);
        addChatButton.setVisibility(View.GONE);
        classDetailList.setVisibility(View.GONE);
    }

    private void enrolled() {
        enrollButton.setVisibility(View.GONE);
        classDetailList.setVisibility(View.VISIBLE);
        eventButton.setVisibility(View.VISIBLE);
        addEventButton.setVisibility(View.VISIBLE);
        chatButton.setVisibility(View.VISIBLE);
        addChatButton.setVisibility(View.VISIBLE);
    }

    private void refreshButtons() {
        if (ParseUser.getCurrentUser() != null) {
            if (enrolled) {
                enrolled();
            } else {
                notEnrolled();
                enrollButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        enrollButton.setVisibility(View.GONE);
                        enrolled = true;
                        refreshButtons();
                    }
                });
            }
        } else {
            notLoggedIn();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        refreshButtons();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.class_detail, container, false);

        classDetailList = (ListView) view.findViewById(R.id.classDetailList);
        enrollButton = view.findViewById(R.id.enrollButton);
        eventButton = view.findViewById(R.id.eventButton);
        addEventButton = view.findViewById(R.id.addEventButton);
        chatButton = view.findViewById(R.id.chatButton);
        addChatButton = view.findViewById(R.id.addChatButton);

        if (getArguments() != null) {
            String classID = getArguments().getString("class");
            selectedClass = ParseUtilities.getClass(classID);
        } else {
            getActivity().getSupportFragmentManager().popBackStack();
        }

        refreshButtons();

        if (selectedClass != null) {
            ((TextView)view.findViewById(R.id.className)).setText(selectedClass.getClassTitle());
            ((TextView)view.findViewById(R.id.classEnrolled)).setText(selectedClass.getEnrollCount());
            classEvents = new EventQueryAdapter(getActivity(), selectedClass);
            classDetailList.setAdapter(classEvents);
        }

        view.findViewById(R.id.eventButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (classEvents != null) {
                    ((ListView)getView().findViewById(R.id.classDetailList)).setAdapter(classEvents);
                }
            }
        });

        addEventButton.setOnClickListener(new View.OnClickListener() {
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

        chatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        addChatButton.setOnClickListener(new View.OnClickListener() {
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
