package com.example.giacomo.studymate;

import android.graphics.Color;
import android.graphics.RectF;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alamkanak.weekview.WeekView;
import com.alamkanak.weekview.WeekViewEvent;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class InWeekView
{

    private WeekView mWeekView;
    private View rootView = null;
    //private View button;

    List<WeekViewEvent> events = new ArrayList<WeekViewEvent>();

    public InWeekView(LayoutInflater inflater, ViewGroup container)
    {

        rootView = inflater.inflate(R.layout.week_layout, container, false);

       // button = rootView.findViewById(R.id.add_button);

        mWeekView = (WeekView) rootView.findViewById(R.id.weekView);

        mWeekView.setMonthChangeListener(new WeekView.MonthChangeListener() {
            @Override
            public List<WeekViewEvent> onMonthChange(int newYear, int newMonth) {

               /*     Esempio inserimento evento:

                    Calendar startTime = Calendar.getInstance();
                    startTime.set(Calendar.HOUR_OF_DAY, 3);
                    startTime.set(Calendar.MINUTE, 0);
                    startTime.set(Calendar.MONTH, newMonth - 1);
                    startTime.set(Calendar.YEAR, newYear);
                    Calendar endTime = (Calendar) startTime.clone();
                    endTime.add(Calendar.HOUR, 1);
                    endTime.set(Calendar.MONTH, newMonth - 1);
                    WeekViewEvent event = new WeekViewEvent(1, getEventTitle(startTime), startTime, endTime);
                    event.setColor(Color.GREEN);
                    events.add(event);*/

                return events;
            }
        });

        mWeekView.setEmptyViewClickListener(new WeekView.EmptyViewClickListener()
        {
            @Override
            public void onEmptyViewClicked(Calendar calendar)
            {

                Calendar endTime = (Calendar) calendar.clone();
                Calendar startTime = (Calendar) calendar.clone();

                startTime.set(Calendar.HOUR, 0);
                startTime.set(Calendar.MINUTE, 0);


                endTime.set(Calendar.HOUR, 23);
                endTime.set(Calendar.MINUTE, 59);

                WeekViewEvent event = new WeekViewEvent(0, "", startTime, endTime);

                for( WeekViewEvent e : events  )
                {
                    int id = (int) e.getId();

                    if( id == 0 )
                        events.remove( e );

                }

                event.setColor(Color.green(1));

                events.add(event);

                mWeekView.notifyDatasetChanged();


                View button = rootView.findViewById(R.id.add_button);
                button.setVisibility(View.VISIBLE);


            }
        });






    }//Fine


    public View getView()
    {
        return this.rootView;
    }


    private String getEventTitle(Calendar time)
    {
        return String.format("Event of %02d:%02d %s/%d", time.get(Calendar.HOUR_OF_DAY), time.get(Calendar.MINUTE), time.get(Calendar.MONTH) + 1, time.get(Calendar.DAY_OF_MONTH));
    }


    public void onEventClick(WeekViewEvent event, RectF eventRect)
    {
        //Toast.makeText(MainActivity.this, "Clicked " + event.getName(), Toast.LENGTH_SHORT).show();
    }


    public void onEventLongPress(WeekViewEvent event, RectF eventRect)
    {
        //Toast.makeText(MainActivity.this, "Long pressed event: " + event.getName(), Toast.LENGTH_SHORT).show();
    }



}
