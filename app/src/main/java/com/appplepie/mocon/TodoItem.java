package com.appplepie.mocon;

public class TodoItem {
    private String Title;
    private String desc;

    public TodoItem(String heading, String desc) {
        this.Title = heading;
        this.desc = desc;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
