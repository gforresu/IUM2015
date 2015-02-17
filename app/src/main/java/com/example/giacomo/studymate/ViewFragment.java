package com.example.giacomo.studymate;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.disegnator.robotocalendar.RobotoCalendarView;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by giacomo on 13/02/2015.
 * La classe si occupa di creare una vista da inserire nel content_frame a seconda della
 * scelta effettuata dall'utente nel menu laterale
 */
public class ViewFragment extends Fragment
{
    public static final String TYPE_CALENDAR_VIEW = "type_view";
    public static final String AUTO_COMPLETE = "magheggio";

    private View bottone ;

    public ViewFragment()
    {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {

        int i = getArguments().getInt(TYPE_CALENDAR_VIEW);

        View rootView = null;

        switch (i)
        {
            case 1:
            {
                InWeekView week = new InWeekView(inflater, container, getArguments().getBoolean(AUTO_COMPLETE));

                rootView = week.getView();

            } break;

            case 0: {



                final InMonthView month = new InMonthView(inflater, container, getArguments().getBoolean(AUTO_COMPLETE));

                rootView = month.getView();

                bottone = month.getBottone();

                bottone.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        CreateEventView event = new CreateEventView();


                        Bundle args = new Bundle();
                        args.putString(event.EVENT_DATE, month.getSelection());
                        event.setArguments(args);

                        FragmentManager fragmentManager = getFragmentManager();
                        fragmentManager.beginTransaction()
                                .replace(R.id.content_frame, event)
                                .commit();

                    }

                });

            }break;

            //Vista del giorno
            case 2:
            {

                   InDayView day = new InDayView(inflater, container);

                   rootView = day.getView();


            }break;

            //Vista delle impostazioni
            case 3:
            {

            }break;

        }

        return rootView;
    }



}