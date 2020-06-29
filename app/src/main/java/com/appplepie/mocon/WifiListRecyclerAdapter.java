package com.appplepie.mocon;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.appplepie.mocon.ui.calendar.CalendarRemainderRecyclerAdapter;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class WifiListRecyclerAdapter  extends RecyclerView.Adapter<WifiListRecyclerAdapter.ItemViewHolder>{
    ArrayList<WifiPlace> wifiPlaceArrayList;

    public WifiListRecyclerAdapter(ArrayList<WifiPlace> wifiPlaceArrayList) {
        this.wifiPlaceArrayList = wifiPlaceArrayList;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.wifi_place_list_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.title.setText(wifiPlaceArrayList.get(position).place);
        holder.wifis.setText(wifiPlaceArrayList.get(position).wifi.toString());
    }

    @Override
    public int getItemCount() {
        if (wifiPlaceArrayList == null){
            return 0;
        }
        return wifiPlaceArrayList.size();
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        TextView wifis;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.wifiListItemTitle);
            wifis = itemView.findViewById(R.id.wifiListItemWifis);
        }
    }
}
