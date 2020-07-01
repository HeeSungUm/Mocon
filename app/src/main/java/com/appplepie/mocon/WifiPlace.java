package com.appplepie.mocon;


import com.appplepie.mocon.ui.todo.TodoItem;

import java.util.ArrayList;

public class WifiPlace {
    public ArrayList<TodoItem> todoItems;
    public String place;
    public ArrayList<String> wifi;

    public String getPlace() {
        return place;
    }

    public ArrayList<String> getWifi() {
        return wifi;
    }

    public WifiPlace(String place, ArrayList<String> wifi) {
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

    public void setWifi(ArrayList<String> wifi) {
        this.wifi = wifi;
    }
}


