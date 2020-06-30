package com.appplepie.mocon;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.Calendar;

public class AddTodoActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    private static final String TAG = "AddTodoActivity";
    EditText placeEt, descEt, timeEt;
    Button submitBtn;
    private int year, month, day;
    private int hour, minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_todo);
        placeEt = findViewById(R.id.addPlanPlaceDropDown);
        descEt = findViewById(R.id.addPlanDescInput);
        submitBtn = findViewById(R.id.addPlanSubmitButton);
        timeEt = findViewById(R.id.AddPlanTimeEditText);

        timeEt.setOnClickListener(view -> {
            Log.e("addtodo", "yes");
            Calendar now = Calendar.getInstance();
            DatePickerDialog dpd = DatePickerDialog.newInstance(
                    AddTodoActivity.this,
                    now.get(Calendar.YEAR),
                    now.get(Calendar.MONTH),
                    now.get(Calendar.DAY_OF_MONTH)
            );
            dpd.setAccentColor("#6200EE");
            dpd.setCancelColor(Color.WHITE);
            dpd.setOkColor(Color.WHITE);
            dpd.show(getSupportFragmentManager(), "DatepickerDialog");


        });

        submitBtn.setOnClickListener(view -> {
            if (!descEt.getText().toString().equals("")) {
                Intent intent = getIntent();
                intent.putExtra("desc", descEt.getText().toString());
                intent.putExtra("place", placeEt.getText().toString());
                intent.putExtra("date", "" + year + "/" + (month + 1) + "/" + day);
                intent.putExtra("time", "" + hour + ":" + this.minute);
                setResult(111, intent);
                finish();
            } else {
                descEt.setError("메모를 입력해주세요");
            }
        });


    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        this.year = year;
        month = monthOfYear;
        day = dayOfMonth;
        Log.e("Datepicker", "" + this.year + "/" + month + "/" + day);
        Calendar now = Calendar.getInstance();
        TimePickerDialog tpd = TimePickerDialog.newInstance(
                AddTodoActivity.this,
                now.get(Calendar.HOUR_OF_DAY),
                now.get(Calendar.MINUTE),
                false
        );
        tpd.setAccentColor("#6200EE");
        tpd.setCancelColor(Color.WHITE);
        tpd.setOkColor(Color.WHITE);
        tpd.show(getSupportFragmentManager(), "DatepickerDialog");
    }

    @Override
    public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {
        hour = hourOfDay;
        this.minute = minute;
        Log.e("Timepicker", "" + hour + ":" + this.minute);
        if (this.minute < 10) {
            timeEt.setText(year + "/" + (month + 1) + "/" + day + " " + hour + ":0" + this.minute);
        } else {
            timeEt.setText(year + "/" + (month + 1) + "/" + day + " " + hour + ":" + this.minute);
        }

    }

    void diaryNotification(Calendar calendar) {
//        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);
//        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
//        Boolean dailyNotify = sharedPref.getBoolean(SettingsActivity.KEY_PREF_DAILY_NOTIFICATION, true);

        PackageManager pm = this.getPackageManager();
        ComponentName receiver = new ComponentName(this, DeviceBootReceiver.class);
        Intent alarmIntent = new Intent(this, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, alarmIntent, 0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Log.e(TAG, "diaryNotification: "+calendar.getTimeInMillis() );
        alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);

        if (PendingIntent.getBroadcast(this, 0, alarmIntent, 0) != null && alarmManager != null) {
            alarmManager.cancel(pendingIntent);
            Toast.makeText(this,"Notifications were disabled",Toast.LENGTH_SHORT).show();
        }
        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP);
    }
}