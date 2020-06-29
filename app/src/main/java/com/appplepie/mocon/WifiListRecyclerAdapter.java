package com.appplepie.mocon;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class WifiListRecyclerAdapter  extends RecyclerView.Adapter<WifiListRecyclerAdapter.ItemViewHolder>{
    ArrayList<WifiPlace> wifiPlaceArrayList;

    public WifiListRecyclerAdapter(ArrayList<WifiPlace> wifiPlaceArrayList) {
        this.wifiPlaceArrayList = wifiPlaceArrayList;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return wifiPlaceArrayList.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder{

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
