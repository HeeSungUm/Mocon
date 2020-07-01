package com.appplepie.mocon.ui.calendar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
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

public class CalendarRemainderRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Activity activity;
    private String place = "";
    ArrayList<TodoItem> itemArrayList;
    ArrayList<TodoItem> headerArrayList = new ArrayList<>();
    ArrayList<TodoItem> currentPlaceList = new ArrayList<>();
    ArrayList<TodoItem> placeList = new ArrayList<>();
    private final int TYPE_CURRENT_HEADER = 11;
    private final int TYPE_HEADER = 111;
    private final int TYPE_CURRENT_ITEM = 2;
    private final int TYPE_ITEM = 3;

    public CalendarRemainderRecyclerAdapter(ArrayList<TodoItem> itemArray, Activity activity) {
        this.itemArrayList = itemArray;
        this.activity = activity;
    }

    public CalendarRemainderRecyclerAdapter(Activity activity, String place, ArrayList<TodoItem> itemArrayList) {
        this.activity = activity;
        this.place = place;
        this.itemArrayList = itemArrayList;
        for (int i = 0; i<itemArrayList.size(); i++){
            if (itemArrayList.get(i).getPlace().equals(place)){
                currentPlaceList.add(itemArrayList.get(i));
            }
            else{
                placeList.add(itemArrayList.get(i));
            }
        }
        for (int i = 0 ; i<itemArrayList.size(); i++){
            if (i>=currentPlaceList.size()){
                headerArrayList.add(placeList.get(i-currentPlaceList.size()));
            }else{
                headerArrayList.add(currentPlaceList.get(i));
            }


        }

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.calender_reminder_item, viewGroup, false);
        View view1 = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.todo_header_item, viewGroup, false);
        ItemViewHolder itemViewHolder = new ItemViewHolder(view, activity);
        if (viewType == TYPE_CURRENT_HEADER || viewType == TYPE_HEADER){
            return new HeaderViewHolder(view1);
        }


        return itemViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder){

            if (position < currentPlaceList.size()+1 && currentPlaceList.size()>=1){
                int pos = position-1;
                ItemViewHolder viewHolder = ((ItemViewHolder) holder);
                Log.e("reverse", "onBindViewHolder: "+position+" "+itemArrayList.get(pos).getTitle() );
                viewHolder.heading_TextView.setText(itemArrayList.get(pos).getTitle());
                viewHolder.desc_TextView.setText(itemArrayList.get(pos).getPlace());
                viewHolder.checkBox.setOnClickListener(view -> {
                    if (viewHolder.checkBox.isChecked() != itemArrayList.get(pos).isChecked()){
                        itemArrayList.get(pos).setChecked(viewHolder.checkBox.isChecked());
                    }
                });
                viewHolder.checkBox.setChecked(itemArrayList.get(pos).isChecked());
            }
            else if (currentPlaceList.size() ==0 || placeList.size() == 0){
                int pos = position;
                ItemViewHolder viewHolder = ((ItemViewHolder) holder);
                viewHolder.heading_TextView.setText(itemArrayList.get(pos).getTitle());
                viewHolder.desc_TextView.setText(itemArrayList.get(pos).getPlace());
                viewHolder.checkBox.setOnClickListener(view -> {
                    if (viewHolder.checkBox.isChecked() != itemArrayList.get(pos).isChecked()){
                        itemArrayList.get(pos).setChecked(viewHolder.checkBox.isChecked());
                    }
                });
                viewHolder.checkBox.setChecked(itemArrayList.get(pos).isChecked());
            }
//            else if(currentPlaceList.size()==0){
//                int pos = position-1;
//                ItemViewHolder viewHolder = ((ItemViewHolder) holder);
//                viewHolder.heading_TextView.setText(itemArrayList.get(pos).getTitle());
//                viewHolder.desc_TextView.setText(itemArrayList.get(pos).getPlace());
//                viewHolder.checkBox.setOnClickListener(view -> {
//                    if (viewHolder.checkBox.isChecked() != itemArrayList.get(pos).isChecked()){
//                        itemArrayList.get(pos).setChecked(viewHolder.checkBox.isChecked());
//                    }
//                });
//                viewHolder.checkBox.setChecked(itemArrayList.get(pos).isChecked());
//            }
            else{
                int pos = position-2;
                ItemViewHolder viewHolder = ((ItemViewHolder) holder);
                viewHolder.heading_TextView.setText(itemArrayList.get(pos).getTitle());
                viewHolder.desc_TextView.setText(itemArrayList.get(pos).getPlace());
                viewHolder.checkBox.setOnClickListener(view -> {
                    if (viewHolder.checkBox.isChecked() != itemArrayList.get(pos).isChecked()){
                        itemArrayList.get(pos).setChecked(viewHolder.checkBox.isChecked());
                    }
                });
                viewHolder.checkBox.setChecked(itemArrayList.get(pos).isChecked());
            }

        }
        else if (holder instanceof HeaderViewHolder){
            HeaderViewHolder viewHolder = ((HeaderViewHolder) holder);
            if (position ==0 && currentPlaceList.size()!=0){
                Log.e("asd", "onBindViewHolder: "+place );
                viewHolder.header.setText(place);
            }
            else {
                viewHolder.header.setText("기타");
            }
        }

    }

    @Override
    public int getItemCount() {
        if (!place.equals("") && currentPlaceList.size()>=1 &&itemArrayList.size()>=2 ){
            return itemArrayList.size()+2;
        }
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
        if (!place.equals("") && currentPlaceList!=null && currentPlaceList.size()>0 && placeList.size()>0  ){
            if (position==0){
                return TYPE_CURRENT_HEADER;
            }
            else if (position == currentPlaceList.size()+2){
                return TYPE_HEADER;
            }
            else if (position > currentPlaceList.size()+2){
                return TYPE_ITEM;
            }
            else{
                return TYPE_CURRENT_ITEM;
            }
        }
        return super.getItemViewType(position);
    }

    class HeaderViewHolder extends RecyclerView.ViewHolder {
        TextView header;
        HeaderViewHolder(View headerView) {
            super(headerView);
            header = headerView.findViewById(R.id.headingText);
        }
    }

}
