package com.appplepie.mocon.ui.calendar;

import androidx.core.content.res.ResourcesCompat;
import androidx.lifecycle.ViewModelProviders;

import android.content.res.Resources;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.applandeo.materialcalendarview.CalendarView;
import com.appplepie.mocon.R;
import com.appplepie.mocon.TodoItem;

import java.util.ArrayList;


public class CalendarFragment extends Fragment {
    CalendarView calendarView;
    RecyclerView recyclerView;
    TextView emptyTextView;
    private ArrayList<TodoItem> calendarItemArrayList = new ArrayList<>();
    private CalendarRemainderRecyclerAdapter recyclerAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        CalendarViewModel mViewModel = ViewModelProviders.of(this).get(CalendarViewModel.class);
        View root = inflater.inflate(R.layout.fragment_calendar, container, false);
        recyclerView = root.findViewById(R.id.calendarRecyclerView);
        calendarView = root.findViewById(R.id.calendarView);
        emptyTextView = root.findViewById(R.id.emptyTextView);


        Resources resources = getResources();
        calendarView.setForwardButtonImage(ResourcesCompat.getDrawable(resources ,R.drawable.ic_baseline_navigate_next_24, null));
        calendarView.setPreviousButtonImage(ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_navigate_before_24, null));
        calendarView.setOnDayClickListener(eventDay -> {

        });

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);

        recyclerAdapter =new CalendarRemainderRecyclerAdapter(calendarItemArrayList);
        recyclerView.setAdapter(recyclerAdapter);

//        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
//                manager.getOrientation());
//        recyclerView.addItemDecoration(dividerItemDecoration);

        TodoItem item = new TodoItem("안녕","하세요");
        calendarItemArrayList.add(item);
        calendarItemArrayList.add(item);
        calendarItemArrayList.add(item);
        calendarItemArrayList.add(item);
        calendarItemArrayList.add(item);
        calendarItemArrayList.add(item);
        recyclerAdapter.notifyDataSetChanged();



        return root;
    }
}