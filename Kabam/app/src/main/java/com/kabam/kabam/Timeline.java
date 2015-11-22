package com.kabam.kabam;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * Created by Ayush on 11/22/15.
 */
public class Timeline extends ListFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.classes, container, false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setListAdapter(new TimelineQueryAdapter(getActivity()));
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

//        String selectedClassObjectID = ((ClassQueryAdapter)getListAdapter()).getItem(position).getObjectId();
//        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
//        Bundle bundle = new Bundle();
//        bundle.putString("class", selectedClassObjectID);
//        ClassDetail classDetail = new ClassDetail();
//        classDetail.setArguments(bundle);
//        ft.replace(R.id.fragmentContainer, classDetail);
//        ft.addToBackStack("class detail");
//        ft.commit();
    }
}
