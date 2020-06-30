package com.appplepie.mocon;

public class TodoItem {
    private String title;
    private String place;
    private String time = "";
    private String date = "";

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    private boolean checked = false;

    public TodoItem(String title, String desc, String time, String date) {
        this.title = title;
        this.place = desc;
        this.time = time;
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public TodoItem(String title, String desc) {
        this.title = title;
        this.place = desc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return place;
    }

    public void setDesc(String desc) {
        this.place = desc;
    }
}
