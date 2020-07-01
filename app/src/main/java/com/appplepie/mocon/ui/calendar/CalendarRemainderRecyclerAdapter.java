package com.appplepie.mocon.ui.calendar;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.appplepie.mocon.ui.todo.AddTodoActivity;
import com.appplepie.mocon.R;
import com.appplepie.mocon.ui.todo.TodoItem;
import com.google.gson.Gson;

import java.util.ArrayList;

public class CalendarRemainderRecyclerAdapter extends RecyclerView.Adapter<CalendarRemainderRecyclerAdapter.ItemViewHolder> {
    private Activity activity;
    ArrayList<TodoItem> itemArrayList;
    SharedPreferences preferences;

    public CalendarRemainderRecyclerAdapter(ArrayList<TodoItem> itemArray, Activity activity) {
        this.itemArrayList = itemArray;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.calender_reminder_item, viewGroup, false);

        return new ItemViewHolder(view, activity);

    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        int pos = position;
        holder.heading_TextView.setText(itemArrayList.get(pos).getTitle());
        holder.desc_TextView.setText(itemArrayList.get(pos).getPlace());
        holder.checkBox.setOnClickListener(view -> {
            if (holder.checkBox.isChecked() != itemArrayList.get(pos).isChecked()) {
                itemArrayList.get(pos).setChecked(holder.checkBox.isChecked());
                Gson gson = new Gson();
                String jsonText = gson.toJson(itemArrayList);
                preferences = PreferenceManager.getDefaultSharedPreferences(activity.getApplicationContext());
                preferences.edit().putString("TodoItems", jsonText).apply();
            }
        });
        holder.checkBox.setChecked(itemArrayList.get(pos).isChecked());


    }

    @Override
    public int getItemCount() {
        return itemArrayList.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView heading_TextView;
        TextView desc_TextView;
        CheckBox checkBox;
        TextView delTv;

        public ItemViewHolder(@NonNull View itemView, Activity activity) {
            super(itemView);
            delTv = itemView.findViewById(R.id.itemDelTv);
            heading_TextView = itemView.findViewById(R.id.calendar_remind_heading);
            desc_TextView = itemView.findViewById(R.id.calendar_remind_desc);
            checkBox = itemView.findViewById(R.id.calendar_remind_checkbox);
            delTv.setOnClickListener(view -> {
                int position = getAdapterPosition();
                itemArrayList.remove(position);
                Gson gson = new Gson();
                String jsonText = gson.toJson(itemArrayList);
                preferences = PreferenceManager.getDefaultSharedPreferences(activity.getApplicationContext());
                preferences.edit().putString("TodoItems", jsonText).apply();
                notifyDataSetChanged();
            });
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

}
