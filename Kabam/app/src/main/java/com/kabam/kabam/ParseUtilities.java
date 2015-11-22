package com.kabam.kabam;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseRelation;
import com.parse.ParseUser;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Created by Ayush on 11/21/15.
 */
public class ParseUtilities {

    private static HashMap<String, ParseUser> allUsers;
    private static HashMap<String, Class> allClasses;

    public static void updateAllUsers() {
        ParseQuery<ParseUser> userQuery = ParseUser.getQuery();
        userQuery.findInBackground(new FindCallback<ParseUser>() {
            public void done(List<ParseUser> results, ParseException e) {
                if (e == null) {
                    allUsers = new HashMap<>();
                    for (int i = 0; i < results.size(); i++) {
                        allUsers.put(results.get(i).getObjectId(), results.get(i));
                    }
                }
            }
        });
    }

//    public static HashMap<String, ParseUser> getAllUsersInClass(Class selectedClass) {
//        final HashMap<String, ParseUser> users = new HashMap<>();
//        ParseQuery<ParseUser> userQuery = ParseUser.getQuery();
//
//        ParseQuery<Class> classQuery = ParseQuery.getQuery("Class");
//        classQuery.whereEqualTo("objectId", selectedClass.getObjectId());
//
//        userQuery.whereMatchesQuery("enrolled", classQuery);
//        List<ParseUser> results = userQuery.find();
//
//        userQuery.findInBackground(new FindCallback<ParseUser>() {
//            @Override
//            public void done(List<ParseUser> results, ParseException e) {
//                for (int i = 0; i < results.size(); i++) {
//                    users.put(results.get(i).getObjectId(), results.get(i));
//                }
//            }
//        });
//    }

    public static Set getUserList() {
        return allUsers.keySet();
    }

    public static void updateAllClasses() {
        ParseQuery<Class> classQuery = ParseQuery.getQuery("Class");
        classQuery.findInBackground(new FindCallback<Class>() {
            @Override
            public void done(List<Class> results, ParseException e) {
                if (e == null) {
                    allClasses = new HashMap<>();
                    for (int i = 0; i < results.size(); i++) {
                        allClasses.put(results.get(i).getObjectId(), results.get(i));
                    }
                }
            }
        });
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
