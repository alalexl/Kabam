package com.kabam.kabam;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by SmrtAsian on 11/20/15.
 */
public class Search extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search, container, false);

        Button searchButton = (Button)view.findViewById(R.id.searchButton);
        final EditText searchField = (EditText)view.findViewById(R.id.searchField);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!searchField.getText().toString().matches("")){ //if search field is not empty
                    Log.d("Message", "Search field was not empty");
                }
                else{ //search field cannot be empty
                    Log.d("Message", "Search field was empty");
                    displayErrorMessage("You cannot leave search field field empty!");

                }
            }
        });
        return view;
    }

    private void displayErrorMessage(String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
        alertDialog.setTitle("Search Error");
        alertDialog.setMessage(message);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialog.show();
    }
}