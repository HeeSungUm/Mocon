package com.appplepie.mocon;


import java.util.ArrayList;
import java.util.Set;

public class WifiPlace {
    public ArrayList<TodoItem> todoItems;
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

    public ArrayList<TodoItem> getTodoItems() {
        return todoItems;
    }

    public void setTodoItems(ArrayList<TodoItem> todoItems) {
        this.todoItems = todoItems;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setWifi(Set<String> wifi) {
        this.wifi = wifi;
    }
}


