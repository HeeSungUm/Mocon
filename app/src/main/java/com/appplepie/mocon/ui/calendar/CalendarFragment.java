package com.appplepie.mocon.ui.calendar;

import androidx.core.content.res.ResourcesCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.applandeo.materialcalendarview.CalendarView;
import com.appplepie.mocon.R;

import java.util.Date;

import static androidx.constraintlayout.widget.Constraints.TAG;


public class CalendarFragment extends Fragment {
    CalendarView calendarView;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        CalendarViewModel mViewModel = ViewModelProviders.of(this).get(CalendarViewModel.class);
        View root = inflater.inflate(R.layout.fragment_calendar, container, false);
        calendarView = root.findViewById(R.id.calendarView);
        Resources resources = getResources();
        calendarView.setForwardButtonImage(ResourcesCompat.getDrawable(resources ,R.drawable.ic_baseline_navigate_next_24, null));
        calendarView.setPreviousButtonImage(ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_navigate_before_24, null));

        return root;
    }
}