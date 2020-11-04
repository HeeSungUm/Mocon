package com.appplepie.mocon.ui.calendar;

import androidx.core.content.res.ResourcesCompat;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.content.res.Resources;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.applandeo.materialcalendarview.CalendarView;
import com.appplepie.mocon.R;
import com.appplepie.mocon.ui.todo.AddTodoActivity;
import com.appplepie.mocon.ui.todo.TodoItem;
import com.appplepie.mocon.ui.home.HomeFragment;

import java.util.ArrayList;
import java.util.Calendar;


public class CalendarFragment extends Fragment {
    CalendarView calendarView;
    RecyclerView recyclerView;
    TextView emptyTextView;
    private CalendarRemainderRecyclerAdapter recyclerAdapter;
    ArrayList<TodoItem> items;
    ImageButton addBtn;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_calendar, container, false);
        recyclerView = root.findViewById(R.id.calendarRecyclerView);
        calendarView = root.findViewById(R.id.calendarView);
        emptyTextView = root.findViewById(R.id.emptyTextView);
        addBtn = root.findViewById(R.id.calendar_image_add_btn);

        emptyTextView.setText("");

        Resources resources = getResources();
        calendarView.setForwardButtonImage(ResourcesCompat.getDrawable(resources ,R.drawable.ic_baseline_navigate_next_24, null));
        calendarView.setPreviousButtonImage(ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_navigate_before_24, null));

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);

        items = new ArrayList<>();

        for(TodoItem item:HomeFragment.todoItems){
            String date = item.getDate();
            if (date != null){
                String[] elements = date.split("/");
                int year = Integer.parseInt(elements[0]);
                int month = Integer.parseInt(elements[1]);
                int day = Integer.parseInt(elements[2]);

                Log.e("dates",""+year+"/"+month+"/"+day);

                if((year==calendarView.getSelectedDate().get(Calendar.YEAR)) && (month==(calendarView.getSelectedDate().get(Calendar.MONTH)+1)) && (day==calendarView.getSelectedDate().get(Calendar.DAY_OF_MONTH))){
                    items.add(item);
                    Log.e("c",item.getTitle());
                }
            }
            }

        if(items.size()==0){
            emptyTextView.setText("(계획 없음)");
        }

        recyclerAdapter =new CalendarRemainderRecyclerAdapter(items, getActivity());
        recyclerView.setAdapter(recyclerAdapter);

        recyclerAdapter.notifyDataSetChanged();

        calendarView.setOnDayClickListener(eventDay -> {
            emptyTextView.setText("");
            items.clear();
            int year = eventDay.getCalendar().get(Calendar.YEAR);
            int month = eventDay.getCalendar().get(Calendar.MONTH)+1;
            int dayOfMonth = eventDay.getCalendar().get(Calendar.DAY_OF_MONTH);

            for(TodoItem item:HomeFragment.todoItems){
                String date = item.getDate();
                if (date!=null){
                    String[] elements = date.split("/");
                    int arrayYear = Integer.parseInt(elements[0]);
                    int arrayMonth = Integer.parseInt(elements[1]);
                    int arrayDay = Integer.parseInt(elements[2]);

                    if((year==arrayYear) && (month==arrayMonth) && (dayOfMonth==arrayDay)){
                        items.add(item);
                    }
                }
            }
            if(items.size() == 0){
                emptyTextView.setText("(계획 없음)");
            }
            recyclerAdapter.notifyDataSetChanged();
        });

        addBtn.setOnClickListener(view->{
            Intent intent = new Intent(getContext(), AddTodoActivity.class);
            this.startActivityForResult(intent, 111);
        });




        return root;
    }
}