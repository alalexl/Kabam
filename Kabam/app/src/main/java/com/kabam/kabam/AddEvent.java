package com.kabam.kabam;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentManager;
import android.widget.ScrollView;

/**
 * Created by tanke_000 on 11/20/2015.
 */
public class AddEvent extends Fragment {
    public String eventNameString;
    public boolean isAssignment;//true=assignment, false=event
    public String descriptionString;
    public int month,day,year;
    private View view;
    public ScrollView sv;
    public DatePicker dp;
    int year_x,month_x,day_x;
    static final int DIALOG_ID=0;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        view = inflater.inflate(R.layout.add_event, container, false);
        //initally radio button assignment is checked
        RadioButton assignmentRB=(RadioButton)view.findViewById(R.id.addEventIsAssignment);
        assignmentRB.setChecked(true);
        //click date due text
        //tried to get dialog to appear when clicked, but it failed
//
//        EditText dueDateText=(EditText)view.findViewById(R.id.addEventDueDate);
//        dueDateText.setOnFocusChangeListener(new View.OnFocusChangeListener(){
//           @Override
//            public void onFocusChange(View v, boolean hasfocus){
//                if(hasfocus){
//////                    FragmentManager fm = getActivity().getFragmentManager();
//////                    DateDialog dialog = new DateDialog(view);
//////                    dialog.show(fm.beginTransaction(), "DatePicker");
////
////                    DateDialog dialog=new DateDialog(view);
////                    FragmentTransaction ft =getFragmentManager().beginTransaction();
////                    dialog.show(ft,"DatePicker");
//
//                }
//            }
//
//        });

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
                DatePicker datePicker=(DatePicker)view.findViewById(R.id.addEventDatePicker);
                month=datePicker.getMonth()+1;
                day=datePicker.getDayOfMonth();
                year=datePicker.getYear();
                EditText description=(EditText)view.findViewById(R.id.addEventDescription);
                descriptionString=description.getText().toString();
                description.setText(eventNameString + "\n"
                        + isAssignment+"\n"
                        +month+"/"+day+"/"+year
                        + descriptionString+"\n");
//                getActivity().getSupportFragmentManager().popBackStack();
            }

        });
        return view;
    }
    public void signUp(View views){//when clicked sign up gets all info
//        EditText eventName=(EditText)view.findViewById(R.id.addEventEventName);
//        eventNameString=eventName.getText().toString();
//        RadioButton assignmentRB=(RadioButton)view.findViewById(R.id.addEventIsAssignment);
//        RadioButton eventRB=(RadioButton)view.findViewById(R.id.addEventIsEvent);
//        if(assignmentRB.isChecked())isAssignment=true;
//        else{isAssignment=false;}
//
//        eventNameString=eventName.getText().toString();
//        EditText dueDate=(EditText)view.findViewById(R.id.addEventDueDate);
//        dueDateString=eventName.getText().toString();
//        EditText description=(EditText)view.findViewById(R.id.addEventDescription);
//        descriptionString=eventName.getText().toString();
//        description.setText("");
//        description.append(eventNameString + "\n");
//        description.append(isAssignment+"\n");
//        description.append(dueDateString+"\n");
//        description.append(descriptionString+"\n");


    }
}
