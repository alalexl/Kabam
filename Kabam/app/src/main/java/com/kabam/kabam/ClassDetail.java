package com.kabam.kabam;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Alex on 11/19/15.
 */
public class ClassDetail extends Fragment {
    String title, enrolled;

    public ClassDetail() {

    }

    public ClassDetail(Class c) {
        this.title = c.getClassTitle();
        this.enrolled = c.getEnrollCount();
    }

    public void onEventClick(View view) {

    }

    public void onChatClick(View view) {

    }

    public void onAddEventClick(View view) {

    }

    public void onAddChatClick(View view) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.class_detail, container, false);
        ((TextView) view.findViewById(R.id.className)).setText(getArguments().getString("title"));
        ((TextView) view.findViewById(R.id.classEnrolled)).setText(getArguments().getString("enrolled"));
        return view;
    }
}
