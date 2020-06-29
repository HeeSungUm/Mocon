package com.appplepie.mocon.ui.setting;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.appplepie.mocon.R;
import com.appplepie.mocon.WifiListRecyclerAdapter;
import com.appplepie.mocon.WifiPlace;
import com.appplepie.mocon.WifiSetting;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class SettingFragment extends Fragment {
    RecyclerView recyclerView;
    ImageButton addWifiPlace;
    SharedPreferences preferences;

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_setting, container, false);
        recyclerView = root.findViewById(R.id.wifiPlaceRecycler);
        addWifiPlace = root.findViewById(R.id.settingAddWifiPlace);
        preferences = PreferenceManager.getDefaultSharedPreferences(getContext());

        Gson gson = new Gson();
        String json = preferences.getString("WifiPlaceList", "");
        Type type = new TypeToken<ArrayList<WifiPlace>>(){}.getType();
        ArrayList<WifiPlace> wifiPlaces = gson.fromJson(json, type);
        WifiListRecyclerAdapter adapter = new WifiListRecyclerAdapter(wifiPlaces);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        addWifiPlace.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), WifiSetting.class);
            this.startActivityForResult(intent, 1111);
        });

        return root;
    }
}