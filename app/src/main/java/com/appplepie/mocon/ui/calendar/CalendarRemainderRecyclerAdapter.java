package com.appplepie.mocon.ui.calendar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.appplepie.mocon.AddTodoActivity;
import com.appplepie.mocon.R;
import com.appplepie.mocon.TodoItem;
import com.appplepie.mocon.ui.TodoActivity;

import java.util.ArrayList;

public class CalendarRemainderRecyclerAdapter extends RecyclerView.Adapter<CalendarRemainderRecyclerAdapter.ItemViewHolder> {
    private Activity activity;
    private String place = "";
    ArrayList<TodoItem> itemArrayList;
    ArrayList<TodoItem> headerArrayList = new ArrayList<>();
    private final int TYPE_CURRENT_HEADER = 0;
    private final int TYPE_HEADER = 1;
    private final int TYPE_CURRENT_ITEM = 2;
    private final int TYPE_ITEM = 3;

    public CalendarRemainderRecyclerAdapter(ArrayList<TodoItem> itemArray, Activity activity) {
        this.itemArrayList = itemArray;
        this.activity = activity;
    }

    public CalendarRemainderRecyclerAdapter(Activity activity, String place, ArrayList<TodoItem> itemArrayList) {
        ArrayList<TodoItem> currentPlaceList = new ArrayList<>();
        ArrayList<TodoItem> placeList = new ArrayList<>();
        this.activity = activity;
        this.place = place;
        this.itemArrayList = itemArrayList;
        for (int i = 0; i<itemArrayList.size(); i++){
            if (itemArrayList.get(i).getPlace() == place){
                currentPlaceList.add(itemArrayList.get(i));
            }
            else{
                placeList.add(itemArrayList.get(i));
            }
        }
        headerArrayList.addAll(currentPlaceList);
        headerArrayList.addAll(placeList);
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.calender_reminder_item, viewGroup, false);
        ItemViewHolder itemViewHolder = new ItemViewHolder(view, activity);

        return itemViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.heading_TextView.setText(itemArrayList.get(position).getTitle());
        holder.desc_TextView.setText(itemArrayList.get(position).getPlace());
        holder.checkBox.setOnClickListener(view -> {
            if (holder.checkBox.isChecked() != itemArrayList.get(position).isChecked()){
                itemArrayList.get(position).setChecked(holder.checkBox.isChecked());
            }
        });
        holder.checkBox.setChecked(itemArrayList.get(position).isChecked());
    }

    @Override
    public int getItemCount() {
        return itemArrayList.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder{

        TextView heading_TextView;
        TextView desc_TextView;
        CheckBox checkBox;

        public ItemViewHolder(@NonNull View itemView, Activity activity) {
            super(itemView);
            heading_TextView = itemView.findViewById(R.id.calendar_remind_heading);
            desc_TextView = itemView.findViewById(R.id.calendar_remind_desc);
            checkBox = itemView.findViewById(R.id.calendar_remind_checkbox);
            itemView.setOnClickListener(view -> {
                int position = getAdapterPosition();
                Intent intent = new Intent(activity.getApplicationContext(), AddTodoActivity.class);
                intent.putExtra("desc", itemArrayList.get(position).getTitle());
                intent.putExtra("place", itemArrayList.get(position).getPlace());
                intent.putExtra("date", itemArrayList.get(position).getDate());
                intent.putExtra("time", itemArrayList.get(position).getTime());
                activity.setResult(11111, intent);
                activity.startActivity(intent);
            });
        }

    }

    @Override
    public int getItemViewType(int position) {
        if (!place.equals("")){

        }
        return super.getItemViewType(position);
    }

    class HeaderViewHolder extends RecyclerView.ViewHolder {

        HeaderViewHolder(View headerView) {
            super(headerView);
        }
    }

}
