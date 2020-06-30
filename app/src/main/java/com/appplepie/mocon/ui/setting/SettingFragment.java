package com.appplepie.mocon.ui.setting;

import android.content.DialogInterface;
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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
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

        Log.e(TAG, "onCreateView: entered "+wifiPlaces );
        WifiListRecyclerAdapter adapter = new WifiListRecyclerAdapter(wifiPlaces);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), manager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setLayoutManager(manager);

        adapter.setOnItemClickListener((v, position) -> {
            AlertDialog.Builder alertBuilder = new AlertDialog.Builder(getContext());
            alertBuilder.setTitle("WIFI 삭제");
            alertBuilder.setMessage("삭제하시겠습니까?");

            alertBuilder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    wifiPlaces.remove(position);
                    adapter.notifyDataSetChanged();
                    Toast.makeText(getContext(), "삭제되었습니다.", Toast.LENGTH_SHORT).show();
                }
            });

            alertBuilder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            alertBuilder.show();
        });

        addWifiPlace.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), WifiSetting.class);
            getContext().startActivity(intent);
        });

        return root;
    }
}