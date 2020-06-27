package com.appplepie.mocon.ui.calendar;

import androidx.core.content.res.ResourcesCompat;
import androidx.lifecycle.ViewModelProviders;

import android.content.res.Resources;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.applandeo.materialcalendarview.CalendarView;
import com.appplepie.mocon.R;

import java.util.ArrayList;


public class CalendarFragment extends Fragment {
    CalendarView calendarView;
    RecyclerView recyclerView;

    private ArrayList<CalendarRemainderRecyclerItem> calendarItemArrayList = new ArrayList<>();
    private CalendarRemainderRecyclerAdapter recyclerAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        CalendarViewModel mViewModel = ViewModelProviders.of(this).get(CalendarViewModel.class);
        View root = inflater.inflate(R.layout.fragment_calendar, container, false);
        recyclerView = root.findViewById(R.id.calendarRecyclerView);
        calendarView = root.findViewById(R.id.calendarView);

        Resources resources = getResources();
        calendarView.setForwardButtonImage(ResourcesCompat.getDrawable(resources ,R.drawable.ic_baseline_navigate_next_24, null));
        calendarView.setPreviousButtonImage(ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_navigate_before_24, null));
        calendarView.setOnDayClickListener(eventDay -> {
//            Log.e("Calender",""+calendarView.getSelectedDate().get(Calendar.MONTH)+"월"
//                    +calendarView.getSelectedDate().get(Calendar.DAY_OF_MONTH));
            Log.e("Calendar",""+calendarView.getSelectedDate());
            //9월 12일

        });

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);

        recyclerAdapter =new CalendarRemainderRecyclerAdapter(calendarItemArrayList);
        recyclerView.setAdapter(recyclerAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                manager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        CalendarRemainderRecyclerItem item = new CalendarRemainderRecyclerItem("안녕","하세요");
        calendarItemArrayList.add(item);
        calendarItemArrayList.add(item);
        calendarItemArrayList.add(item);
        calendarItemArrayList.add(item);
        calendarItemArrayList.add(item);
        calendarItemArrayList.add(item);
        recyclerAdapter.notifyDataSetChanged();
        //분명 아이템 6개를 추가했는데 화면에서는 5개밖에 안보이는 문제발생?!
        //n개 추가하면 n-1개밖에 안보임. 1개만 추가한 경우에는 정상적으로 보임.

        return root;
    }
}