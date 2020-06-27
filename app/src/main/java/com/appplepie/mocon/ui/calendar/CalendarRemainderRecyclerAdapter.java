package com.appplepie.mocon.ui.calendar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.appplepie.mocon.R;

import java.util.ArrayList;

public class CalendarRemainderRecyclerAdapter extends RecyclerView.Adapter<CalendarRemainderRecyclerAdapter.ItemViewHolder>{

    ArrayList<CalendarRemainderRecyclerItem> itemArrayList;

    public CalendarRemainderRecyclerAdapter(ArrayList<CalendarRemainderRecyclerItem> itemArray) {
        this.itemArrayList = itemArray;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.calender_reminder_item, viewGroup, false);
        ItemViewHolder itemViewHolder = new ItemViewHolder(view);

        return itemViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.heading_TextView.setText(itemArrayList.get(position).getHeading());
        holder.desc_TextView.setText(itemArrayList.get(position).getDesc());
    }

    @Override
    public int getItemCount() {
        return itemArrayList.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder{

        TextView heading_TextView;
        TextView desc_TextView;
        CheckBox checkBox;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            heading_TextView = itemView.findViewById(R.id.calendar_remind_heading);
            desc_TextView = itemView.findViewById(R.id.calendar_remind_desc);
            checkBox = itemView.findViewById(R.id.checkbox);
        }
    }
}
