package com.appplepie.mocon;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import android.widget.ArrayAdapter;

import android.widget.Button;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    String[] wifiList;
    final String CoarseLocation = Manifest.permission.ACCESS_COARSE_LOCATION;
    final String AccessWifi = Manifest.permission.ACCESS_WIFI_STATE;
    final String ChangeWifi = Manifest.permission.CHANGE_WIFI_STATE;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor= preferences.edit();
        setContentView(R.layout.activity_main);
        getPermission();

        if (preferences.getBoolean("FirstStart", true)){
            preferences.edit().putBoolean("FirstStart", false).apply();
            WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);

            if (!wifiManager.isWifiEnabled()) {
                if (wifiManager.getWifiState() != WifiManager.WIFI_STATE_ENABLING) {
                    wifiManager.setWifiEnabled(true);
                }
                getWifiConfigurations(wifiManager);
            }else{
                getWifiConfigurations(wifiManager);
            }

            MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this);

            LayoutInflater inflater = getLayoutInflater();
            View dialogView = inflater.inflate(R.layout.wifi_setting_dialog, null);
            builder.setView(dialogView);

            MultiAutoCompleteTextView spinner = dialogView.findViewById(R.id.dialogWifiInput);
            EditText editText = dialogView.findViewById(R.id.dialogPlaceInput);

            ArrayAdapter<String> stringArrayAdapter;

            if (wifiList == null || wifiList.length ==0){
                stringArrayAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, new String[]{"사용가능한 와이파이가 없습니다."});
            }else {
                stringArrayAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, wifiList);
            }
            spinner.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
            spinner.setAdapter(stringArrayAdapter);
            builder.setPositiveButton("확인", null);


            AlertDialog dialog = builder.create();
            dialog.setCanceledOnTouchOutside(false);
            dialog.setOnShowListener(dialogInterface -> {
                Button button = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE);
                button.setOnClickListener(view -> {
                    if (editText.getText().toString().equals("") ^ spinner.getText().toString().equals("")){
                        editText.setError("빈칸을 다 채워주세요");
                    }else{
                        String place = editText.getText().toString();
                        String[] wifi = spinner.getText().toString().split(",");
                        Log.e("tag", "onCreate: "+ Arrays.toString(wifi));
                        editor.putStringSet(place, new HashSet<>(Arrays.asList(wifi)));
                        editor.apply();
                        dialog.dismiss();
                    }
                });
            });
            dialog.show();
        }


        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_calendar , R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }

    void getPermission(){
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
    void getWifiConfigurations(WifiManager wifiManager){
        List<WifiConfiguration> configurations = wifiManager.getConfiguredNetworks();
        Log.e("tag", "onCreate: "+configurations );
        wifiList = new String[configurations.size()];
        if (configurations.size() != 0) {
            for (int i = 0; i < configurations.size(); i++) {
                wifiList[i] = configurations.get(i).SSID;
            }
        }
    }

}