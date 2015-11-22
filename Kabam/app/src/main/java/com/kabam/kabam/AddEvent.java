package com.kabam.kabam;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.ScrollView;

/**
 * Created by tanke_000 on 11/20/2015.
 */
public class AddEvent extends Fragment {
    public String eventNameString;//Event name
    public boolean isAssignment;//true=assignment, false=event
    public String dueDateString;//Event due date
    public String descriptionString;//Event Description

    private View view;
    public ScrollView sv;
    public DatePicker dp;
    static final int DIALOG_ID=0;



    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        view = inflater.inflate(R.layout.add_event, container, false);
        //initally radio button assignment is checked
        RadioButton assignmentRB=(RadioButton)view.findViewById(R.id.addEventIsAssignment);
        assignmentRB.setChecked(true);
        //clicked date_due EditText, DatePicker Pops up
        EditText dueDateText=(EditText)view.findViewById(R.id.addEventDueDate);
        dueDateText.setKeyListener(null);
        dueDateText.setOnFocusChangeListener(new View.OnFocusChangeListener(){
           @Override
            public void onFocusChange(View v, boolean hasfocus){
                if(hasfocus){
                    DialogFragment newFragment = new DateDialog(view);
                    newFragment.show(getActivity().getFragmentManager(), "datePicker");
                 }
            }

        });

        //click button saves data
        Button submitButton=(Button)view.findViewById(R.id.addEventSubmitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                EditText eventName=(EditText)view.findViewById(R.id.addEventEventName);
                eventNameString=eventName.getText().toString();
                RadioButton assignmentRB=(RadioButton)view.findViewById(R.id.addEventIsAssignment);
                RadioButton eventRB=(RadioButton)view.findViewById(R.id.addEventIsEvent);
                if(assignmentRB.isChecked())isAssignment=true;
                else{isAssignment=false;}

                eventNameString=eventName.getText().toString();
                EditText dueDate=(EditText)view.findViewById(R.id.addEventDueDate);
                dueDateString=dueDate.getText().toString();
                EditText description=(EditText)view.findViewById(R.id.addEventDescription);
                descriptionString=description.getText().toString();

                getActivity().getSupportFragmentManager().popBackStack();
            }

        });
        return view;
    }
}
