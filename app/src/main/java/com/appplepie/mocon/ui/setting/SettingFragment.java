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
    TextView emptyTv;

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == 111){
            Gson gson = new Gson();
            String json = preferences.getString("WifiPlaceList", "");
            Type type = new TypeToken<ArrayList<WifiPlace>>(){}.getType();
            ArrayList<WifiPlace> wifiPlaces = gson.fromJson(json, type);
            WifiListRecyclerAdapter adapter = new WifiListRecyclerAdapter(wifiPlaces);
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_setting, container, false);
        recyclerView = root.findViewById(R.id.wifiPlaceRecycler);
        addWifiPlace = root.findViewById(R.id.settingAddWifiPlace);
        emptyTv = root.findViewById(R.id.settingTv);
        emptyTv.setText("");
        preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        SharedPreferences.Editor editor = preferences.edit();

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
        adapter.notifyDataSetChanged();

        if (wifiPlaces!=null){
            if(wifiPlaces.size() == 0){
                emptyTv.setText("목록이 비었습니다.\n장소를 추가하세요.");
            }
            else{
                emptyTv.setText("");
            }
        }else {
            emptyTv.setText("목록이 비었습니다.\n장소를 추가하세요.");
        }



        adapter.setOnItemClickListener((v, position) -> {
            AlertDialog.Builder alertBuilder = new AlertDialog.Builder(getContext());
            alertBuilder.setTitle("WIFI 삭제");
            alertBuilder.setMessage("삭제하시겠습니까?");

            alertBuilder.setPositiveButton("예", (dialogInterface, i) -> {
                wifiPlaces.remove(position);
                adapter.notifyDataSetChanged();
                String jsonText = gson.toJson(wifiPlaces);
                editor.putString("WifiPlaceList", jsonText);
                editor.apply();
                Toast.makeText(getContext(), "삭제되었습니다.", Toast.LENGTH_SHORT).show();
                if(wifiPlaces.size() == 0){
                    emptyTv.setText("목록이 비었습니다.\n장소를 추가하세요.");
                }
            });

            alertBuilder.setNegativeButton("취소", (dialogInterface, i) -> {

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