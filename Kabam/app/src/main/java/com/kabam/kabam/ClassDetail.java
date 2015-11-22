package com.kabam.kabam;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
    private ListView listView;
    private String[] values = {"Not first", "sECdon", "loser"};
    private String title, enrolled;
    private Class clss;

    public ClassDetail() {

    }

    public ClassDetail(Class c) {
        this.title = c.getClassTitle();
        this.enrolled = c.getEnrollCount();
        this.clss = c;

    }

    class ClassDetailListView extends Activity {
        ListView listView;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.class_detail);
            listView = (ListView)findViewById(R.id.classDetailList);
            Set<Event> events = clss.getEvents();
            String[] values = new String[events.size()];
            int c = 0;
            for (Event e : events) {
                values[c] = e.getDescription();
                c++;
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, android.R.id.text1, values);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?>parent, View view, int position, long id) {
                    int itemPosition = position;
                    String itemValue = (String) listView.getItemAtPosition(position);
                    Toast.makeText(getApplicationContext(), "Position :"+itemPosition+" ListItem:"+itemValue,
                            Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    public void onEventClick() {
        String[] asdf = {"Fuck", "Your", "***", "Please", "Don't", "Ask", "When",
                "The", "Next", "Midterm", "Is"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(),
                android.R.layout.simple_list_item_1, asdf);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int itemPosition = position;
                String itemValue = (String) listView.getItemAtPosition(position);
                Toast.makeText(getContext(), "Opening " +itemValue+ " event.",
                        Toast.LENGTH_LONG).show();
            }
        });

    }

    public void onChatClick() {
        String[] asdf = {"Assignment 5 Chat", "We", "Don't", "Give", "A", "Fuck", "About", "Skool", "Baby"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(),
                android.R.layout.simple_list_item_1, asdf);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int itemPosition = position;
                String itemValue = (String) listView.getItemAtPosition(position);
                Toast.makeText(getContext(), "Opening " + itemValue+ " chat.",
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    public void onAddEventClick() {
        Toast.makeText(getContext(), "Added a new Event", Toast.LENGTH_LONG).show();
    }

    public void onAddChatClick() {
        Toast.makeText(getContext(), "Added a new Chat", Toast.LENGTH_LONG).show();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.class_detail, container, false);
        ((TextView) view.findViewById(R.id.className)).setText(getArguments().getString("title"));
        ((TextView) view.findViewById(R.id.classEnrolled)).setText(getArguments().getString("enrolled"));

        listView = (ListView) view.findViewById(R.id.classDetailList);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, values);
        listView.setAdapter(adapter);

        setListeners(view);

        return view;
    }

    public void setListeners(View view) {
        Button eventB = (Button) view.findViewById(R.id.eventButton);
        Button chatB = (Button) view.findViewById(R.id.chatButton);
        Button addEventB = (Button) view.findViewById(R.id.addEventButton);
        Button addChatB = (Button) view.findViewById(R.id.addChatButton);

        eventB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onEventClick();
            }
        });

        chatB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onChatClick();
            }
        });

        addEventB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAddEventClick();
            }
        });

        addChatB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAddChatClick();
            }
        });

    }
}
