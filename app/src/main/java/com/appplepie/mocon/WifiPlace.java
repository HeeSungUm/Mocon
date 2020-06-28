package com.appplepie.mocon;


import java.util.ArrayList;
import java.util.Set;

public class WifiPlace {
    public int id;
    public ArrayList<ToDos> toDos;
    public String place;
    public Set<String> wifi;

    public String getPlace() {
        return place;
    }

    public Set<String> getWifi() {
        return wifi;
    }

    public WifiPlace(String place, Set<String> wifi) {
        this.place = place;
        this.wifi = wifi;
    }
}


