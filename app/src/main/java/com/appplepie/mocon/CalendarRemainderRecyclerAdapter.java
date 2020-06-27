package com.appplepie.mocon;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class CalendarRemainderRecyclerAdapter extends RecyclerView.Adapter<CalendarRemainderRecyclerAdapter.ItemViewHolder>{
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
        return 0;
    }

    class ItemViewHolder extends RecyclerView.ViewHolder{

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
