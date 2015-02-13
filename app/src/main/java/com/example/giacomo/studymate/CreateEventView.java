package com.example.giacomo.studymate;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Crea la vista ed i listener per la creazione dell'evento
 */
public class CreateEventView extends Fragment
{


    public CreateEventView()
    {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {

      /*  View dateStart = rootView.findViewById(R.id."id campo data");
        View dateEnd = rootView.findViewById(R.id."id campo data");
        View hourStart = rootView.findViewById(R.id."id campo data");
        View hourEnd = rootView.findViewById(R.id."id campo data");*/


        return inflater.inflate(R.layout.new_event, container, false);

    }



}
