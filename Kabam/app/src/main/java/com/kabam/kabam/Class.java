package com.kabam.kabam;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by Ayush on 11/19/15.
 */
@ParseClassName("Class")
public class Class extends ParseObject {


    public Class() {

    }

    public String getClassTitle() {
        return getString("title");
    }

    public void setClassTitle(String title) {
        put("title", title);
    }

    public String getEnrollCount() {
        return getNumber("enrolled").intValue() + " Students Enrolled";
    }

    public void setEnrollCount(int count) {
        put("enrolled", count);
    }

}
