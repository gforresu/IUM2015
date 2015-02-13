package com.example.giacomo.studymate;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by giacomo on 13/02/2015.
 * La classe si occupa di creare una vista da inserire nel content_frame a seconda della
 * scelta effettuata dall'utente nel menu laterale
 */
public class ViewFragment extends Fragment
{
    public static final String TYPE_CALENDAR_VIEW = "type_view";

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
                InWeekView week = new InWeekView(inflater, container);

                rootView = week.getView();

            } break;

            case 0:
            {
                InMonthView month = new InMonthView(inflater, container);

                rootView = month.getView();


            } break;

            //Vista del giorno
            case 2:
            {

            }break;

            //Vista delle impostazioni
            case 3:
            {

            }break;

        }

        return rootView;
    }



}