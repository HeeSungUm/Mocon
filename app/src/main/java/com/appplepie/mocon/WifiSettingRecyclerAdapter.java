package com.appplepie.mocon;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

class WifiSettingRecyclerAdapter extends RecyclerView.Adapter<WifiSettingRecyclerAdapter.ItemViewHolder> {
    ArrayList<Boolean> arrayList;
    ArrayList<WifiPlace> wifiPlaces = new ArrayList<>();
    ArrayAdapter<String> adapter;
    String[] wifis;
    Context context;
    Activity mctx;

    public WifiSettingRecyclerAdapter(ArrayList<Boolean> arrayList, Context context, String[] wifis, Activity a) {
        mctx=a;
        this.arrayList = arrayList;
        this.context = context;
        this.wifis = wifis;
        if (wifis== null){
            adapter = new ArrayAdapter<String>(context, android.R.layout.simple_dropdown_item_1line, new String[]{"와이파이가 없습니다"});
        }else{
            adapter = new ArrayAdapter<String>(context, android.R.layout.simple_dropdown_item_1line, wifis);
        }
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.wifi_setting_item, parent, false);
        return new ItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.wifiList.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
        holder.wifiList.setAdapter(adapter);
        holder.placeEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        EditText placeEt;
        MultiAutoCompleteTextView wifiList;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            placeEt = itemView.findViewById(R.id.placeInput);
            wifiList = itemView.findViewById(R.id.wifiList);
        }
    }
}
