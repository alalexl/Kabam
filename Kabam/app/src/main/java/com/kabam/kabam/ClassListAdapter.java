package com.kabam.kabam;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Ayush on 11/19/15.
 */
public class ClassListAdapter extends ArrayAdapter<Class> {

    public ClassListAdapter(Context context, ArrayList<Class> classes) {
        super(context, 0, classes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Class classDetails = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.class_list_item, parent, false);
        }
        // Lookup view for data population
        TextView classTitle = (TextView) convertView.findViewById(R.id.title);
        TextView enrollCount = (TextView) convertView.findViewById(R.id.enrolled);

        // Populate the data into the template view using the data object
        classTitle.setText(classDetails.classTitle);
        enrollCount.setText(classDetails.enrollCount);

        // Return the completed view to render on screen
        return convertView;
    }
}
