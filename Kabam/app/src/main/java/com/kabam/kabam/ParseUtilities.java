package com.kabam.kabam;

import com.parse.ParseUser;

import java.util.HashMap;

/**
 * Created by Ayush on 11/21/15.
 */
public class ParseUtilities {

    private static HashMap<String, ParseUser> allUsers;
    private static HashMap<String, Class> allClasses;

    public static void updateAllUsers() {

    }

    public static void updateAllClasses() {

    }

    public static String getName(String userID) {
        return allUsers.get(userID).get("first_name") + " " + allUsers.get(userID).get("last_name");
    }

    public static ParseUser getUser(String userID) {
        return allUsers.get(userID);
    }

    public static Class getClass(String classID) {
        return allClasses.get(classID);
    }
}
