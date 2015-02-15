package com.example.giacomo.studymate;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alamkanak.weekview.WeekView;
import com.alamkanak.weekview.WeekViewEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by giacomo on 15/02/2015.
 */
public class InDayView
{
    private View rootView;
    private WeekView mDayView;
    List<WeekViewEvent> events = new ArrayList<WeekViewEvent>();

    public InDayView(LayoutInflater inflater, ViewGroup container)
    {

        this.rootView = inflater.inflate(R.layout.day_layout, null);

        mDayView = (WeekView) rootView.findViewById(R.id.dayView);

        mDayView.setMonthChangeListener(new WeekView.MonthChangeListener() {
            @Override
            public List<WeekViewEvent> onMonthChange(int newYear, int newMonth) {

                return events;
            }
        });
    }


    public View getView(){ return this.rootView;  }



}
