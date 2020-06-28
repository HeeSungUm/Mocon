package com.appplepie.mocon.ui.calendar;

import android.widget.CheckBox;

public class CalendarRemainderRecyclerItem {
    private String heading;
    private String desc;
    private boolean isChecked;

    public CalendarRemainderRecyclerItem(String heading, String desc) {
        this.heading = heading;
        this.desc = desc;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
