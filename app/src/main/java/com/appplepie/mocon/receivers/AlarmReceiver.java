package com.appplepie.mocon.receivers;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.appplepie.mocon.NotificationService;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent serviceIntent = new Intent(context, NotificationService.class);
        serviceIntent.putExtra("desc", intent.getStringExtra("desc"));


        Log.e("Receiver", "onReceive: entered " + intent.getStringExtra("desc") );
        context.startService(serviceIntent);
    }
}


