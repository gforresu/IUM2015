package com.example.giacomo.studymate;

import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.disegnator.robotocalendar.RobotoCalendarView;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class InMonthView extends FragmentActivity implements RobotoCalendarView.RobotoCalendarListener
{
    private View rootView = null;
    private RobotoCalendarView robotoCalendarView;
    private Calendar currentCalendar;
    private int currentMonthIndex;
    private View bottone ;

    public InMonthView(LayoutInflater inflater, ViewGroup container)
    {

        rootView = inflater.inflate(R.layout.month_layout, container, false);
        robotoCalendarView = (RobotoCalendarView) rootView.findViewById(R.id.robotoCalendarPicker);

        currentMonthIndex = 0;
        currentCalendar = Calendar.getInstance(Locale.getDefault());

        this.bottone = this.rootView.findViewById(R.id.add_button);

        robotoCalendarView.setRobotoCalendarListener( this );
        robotoCalendarView.initializeCalendar(currentCalendar);
        robotoCalendarView.markDayAsCurrentDay(currentCalendar.getTime());

    }


    public View getView()
    {
        return this.rootView;
    }

    public View getBottone(){ return this.bottone; }


    @Override
    public void onDateSelected(Date date)
    {

        this.robotoCalendarView.markDayAsSelectedDay(date);

        this.bottone.setVisibility(View.VISIBLE);
    }

    @Override
    public void onRightButtonClick()
    {
        this.currentMonthIndex++;
        bottone.setVisibility(View.INVISIBLE);
        this.updateCalendar();

    }

    @Override
    public void onLeftButtonClick()
    {
        this.currentMonthIndex--;
        bottone.setVisibility(View.INVISIBLE);
        this.updateCalendar();
    }

    private void updateCalendar()
    {
        currentCalendar = Calendar.getInstance(Locale.getDefault());
        currentCalendar.add(Calendar.MONTH, currentMonthIndex);
        robotoCalendarView.initializeCalendar(currentCalendar);
    }





}