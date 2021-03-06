package com.appplepie.mocon;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WifiSetting extends AppCompatActivity {
    final String CoarseLocation = Manifest.permission.ACCESS_COARSE_LOCATION;
    final String AccessWifi = Manifest.permission.ACCESS_WIFI_STATE;
    final String ChangeWifi = Manifest.permission.CHANGE_WIFI_STATE;
    private static final String TAG = "WIFISETTING";
    String[] wifiList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi_setting);
        getPermission();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = preferences.edit();

        WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);

        if (!wifiManager.isWifiEnabled()) {
            if (wifiManager.getWifiState() != WifiManager.WIFI_STATE_ENABLING) {
                wifiManager.setWifiEnabled(true);
            }
            getWifiConfigurations(wifiManager);
        } else {
            getWifiConfigurations(wifiManager);
        }

        MultiAutoCompleteTextView spinner = findViewById(R.id.dialogWifiInput);
        EditText editText = findViewById(R.id.wifiPlaceInput);
        Button skipButton = findViewById(R.id.wifiSettingSkip);
        Button summitButton = findViewById(R.id.wifiSettingSubmit);
        ArrayAdapter<String> stringArrayAdapter;

        if (wifiList == null || wifiList.length == 0) {
            stringArrayAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, new String[]{"사용가능한 와이파이가 없습니다."});
        } else {
            stringArrayAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, wifiList);
        }
        spinner.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
        spinner.setAdapter(stringArrayAdapter);

        Intent intent = getIntent();

        summitButton.setOnClickListener(view -> {
            if (editText.getText().toString().equals("") || spinner.getText().toString().equals("")) {
                editText.setError("빈칸을 다 채워주세요");
            } else {
                String place = editText.getText().toString();
                ArrayList<String> wifi = new ArrayList<>(Arrays.asList(spinner.getText().toString().split(",")));

                Gson gson = new Gson();
                String json = preferences.getString("WifiPlaceList", "");
                Type type = new TypeToken<ArrayList<WifiPlace>>(){}.getType();
                ArrayList<WifiPlace> wifiPlaces = gson.fromJson(json, type);
                if (wifiPlaces!=null){
                    wifiPlaces.add(new WifiPlace(place, wifi));
                    String jsonText = gson.toJson(wifiPlaces);
                    Toast.makeText(this, "추가되었습니다", Toast.LENGTH_SHORT).show();
                    editor.putString("WifiPlaceList", jsonText);
                    editor.apply();
                    setResult(111,intent);
                    finish();
                }
                else{
                    ArrayList<WifiPlace> wifiPlaces1 = new ArrayList<>();
                    wifiPlaces1.add(new WifiPlace(place, wifi));
                    String jsonText = gson.toJson(wifiPlaces1);
                    Toast.makeText(this, "추가되었습니다", Toast.LENGTH_SHORT).show();
                    editor.putString("WifiPlaceList", jsonText);
                    editor.apply();
                    setResult(111,intent);
                    finish();
                }
            }
        });
        skipButton.setOnClickListener(view -> finish());
    }
    void getWifiConfigurations (WifiManager wifiManager){
        List<WifiConfiguration> configurations = wifiManager.getConfiguredNetworks();
        Log.e("tag", "onCreate: " + configurations);
        wifiList = new String[configurations.size()];
        if (configurations.size() != 0) {
            for (int i = 0; i < configurations.size(); i++) {
                wifiList[i] = configurations.get(i).SSID;
            }
        }
    }
    void getPermission () {
        if (checkSelfPermission(CoarseLocation) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, 123);
        }

        if (checkSelfPermission(AccessWifi) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_WIFI_STATE, Manifest.permission.ACCESS_WIFI_STATE}, 123);
        }

        if (checkSelfPermission(ChangeWifi) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CHANGE_WIFI_STATE, Manifest.permission.CHANGE_WIFI_STATE}, 123);
        }
    }
}