package com.example.rebelfoodchallengeapp;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import java.util.ArrayList;
import java.util.List;

public class dataWire {
   static List<users>  usersList=  new ArrayList<>();

   static  String url;
   static  Double Lat;
   static  Double Log;
   static  String address;

    public static String getAddress() {
        return address;
    }

    public static void setAddress(String address) {
        dataWire.address = address;
    }

    public static Double getLat() {
        return Lat;
    }

    public static void setLat(Double lat) {
        Lat = lat;
    }

    public static Double getLog() {
        return Log;
    }

    public static void setLog(Double log) {
        Log = log;
    }

    public static String getUrl() {
        return url;
    }

    public static void setUrl(String url) {
        dataWire.url = url;
    }

    static String currentScreen ;
    public static String getCurrentScreen() {
        return currentScreen;
    }

    public static void setCurrentScreen(String currentScreen) {
        dataWire.currentScreen = currentScreen;
    }


    public static List<users> getUsersList() {
        return usersList;
    }

    public static void setUsersList(List<users> usersList) {
        dataWire.usersList = usersList;
    }




}
