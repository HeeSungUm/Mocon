package com.appplepie.mocon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;
import java.util.List;

public class WifiSetting extends AppCompatActivity {
    private static final String TAG = "WIFISETTING";
    Button submitBtn;
    ArrayList<Boolean> arrayList = new ArrayList<>();
    int itemCnt = 2;
    String[] wifiList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final String CoarseLocation = Manifest.permission.ACCESS_COARSE_LOCATION;
        final String AccessWifi = Manifest.permission.ACCESS_WIFI_STATE;
        final String ChangeWifi = Manifest.permission.CHANGE_WIFI_STATE;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi_setting);

        if (checkSelfPermission(CoarseLocation) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, 123);
        }

        if (checkSelfPermission(AccessWifi) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_WIFI_STATE, Manifest.permission.ACCESS_WIFI_STATE}, 123);
        }

        if (checkSelfPermission(ChangeWifi) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CHANGE_WIFI_STATE, Manifest.permission.CHANGE_WIFI_STATE}, 123);
        }

        WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);

        if (!wifiManager.isWifiEnabled()) {
            if (wifiManager.getWifiState() != WifiManager.WIFI_STATE_ENABLING) {
                wifiManager.setWifiEnabled(true);
            }
            List<WifiConfiguration> configurations = wifiManager.getConfiguredNetworks();
            wifiList = new String[configurations.size()];
            if (configurations.size() != 0) {
                for (int i = 0; i < configurations.size(); i++) {
                    wifiList[i] = configurations.get(i).SSID;
                }
            }
        }

        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.wifi_setting_dialog, null);
//        final MultiAutoCompleteTextView completeTextView = dialogView.findViewById(R.id.wifiInput);
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this);
        builder.setView(R.layout.wifi_setting_dialog);
        builder.setPositiveButton("확인", (dialogInterface, i) -> {

        });
        builder.show();

//        WifiSettingRecyclerAdapter adapter = new WifiSettingRecyclerAdapter(arrayList, getApplicationContext(), wifiList, this);

//        recyclerView = findViewById(R.id.wifiSettingRecyclerView);
//        addRecyclerItemBtn = findViewById(R.id.wifiSettingAddItem);
//        addRecyclerItemTv = findViewById(R.id.wifiSettingAddTv);
//        submitBtn = findViewById(R.id.wifiSettingSubmit);
//
//        arrayList.add(true);
//        arrayList.add(true);
//        recyclerView.setAdapter(adapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

//        addRecyclerItemTv.setOnClickListener(v -> {
//            arrayList.add(true);
//            itemCnt++;
//            adapter.notifyDataSetChanged();
//        });
//        addRecyclerItemBtn.setOnClickListener(v -> {
//            arrayList.add(true);
//            itemCnt++;
//            adapter.notifyDataSetChanged();
//        });
//        submitBtn.setOnClickListener(v -> finish());


    }
}