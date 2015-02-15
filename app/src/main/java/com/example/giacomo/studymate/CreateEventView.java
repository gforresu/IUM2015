package com.example.giacomo.studymate;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.SimpleTimeZone;

/**
 * Crea la vista ed i listener per la creazione dell'evento
 */
public class CreateEventView extends Fragment
{
    public static final String EVENT_DATE = "event_date";

    private EditText endDate;
    private EditText startDate;
    private EditText startTime;
    private EditText endTime;

    public CreateEventView()
    {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {

        //Cambio titolo all'actionbar
        getActivity().getActionBar().setTitle("Nuovo evento");

        //Imposto il listener dal datepicker
        DatePickerDialog.OnDateSetListener dateEndPickerListener = new DatePickerDialog.OnDateSetListener()
        {
            public void onDateSet(DatePicker view, int year,int month, int day)
            {



                GregorianCalendar d =new GregorianCalendar(year, month, day);

                SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

                String sel = format.format(d.getTime());

                endDate.setText(sel);
            }
    };
        DatePickerDialog.OnDateSetListener dateStartPickerListener = new DatePickerDialog.OnDateSetListener()
        {
            public void onDateSet(DatePicker view, int year,int month, int day)
            {



                GregorianCalendar d =new GregorianCalendar(year, month, day);

                SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

                String sel = format.format(d.getTime());

                startDate.setText(sel);
            }
        };
        ////////////////////////////////////////////////////


        //Imposto i listener del time picker
        TimePickerDialog.OnTimeSetListener timeStartPickerListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hours, int minutes)
            {
                String ora;

                if(minutes < 10)
                    ora = new String(hours+":"+ "0"+minutes);

                else
                    ora = new String(hours+":"+ minutes);

                startTime.setText(ora);

            }
        };

        TimePickerDialog.OnTimeSetListener timeEndPickerListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hours, int minutes)
            {

                String ora;

                if(minutes < 10)
                    ora = new String(hours+":"+ "0"+minutes);

                else
                    ora = new String(hours+":"+ minutes);

                endTime.setText(ora);

            }
        };
////////////////////////////////


        //Recupero la data
        String data = getArguments().getString(EVENT_DATE);

        final View v = inflater.inflate(R.layout.new_event, container, false);


        //Imposto la data selezionata nel campo della data inizio dell'evento
        final EditText tmpStartDate = (EditText) v.findViewById(R.id.startDate);
        //EditText endDate = (EditText) v.findViewById(R.id.endDate);
        this.startDate = tmpStartDate;
        startDate.setText(data);


        //Picker fragment per la data fine
        final EditText tmpEndDate = (EditText) v.findViewById(R.id.endDate);
        this.endDate = tmpEndDate;


        Calendar cal = Calendar.getInstance();


        //Definizione dei picker della data
        final DatePickerDialog endDateDialog = new DatePickerDialog(getActivity(), dateEndPickerListener ,
                cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH));


        endDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {

                if(tmpEndDate.isFocused())
                    endDateDialog.show();
            }
        });


        final DatePickerDialog startDateDialog = new DatePickerDialog(getActivity(), dateStartPickerListener ,
                cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH));


        startDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {

                if(tmpStartDate.isFocused())
                    startDateDialog.show();
            }
        });



        // int hourOfDay, int minute, boolean is24HourView

       /* Calendar start = Calendar.getInstance();
        start.set(Calendar.HOUR_OF_DAY, 9);
        start.set(Calendar.MINUTE, 00);*/


        //Impostazione dei picker dell'ora

        final TimePickerDialog dialogStartTime = new TimePickerDialog(getActivity(), timeStartPickerListener, 9, 0, true );
        final TimePickerDialog dialogEndTime = new TimePickerDialog(getActivity(), timeEndPickerListener, 9, 0, true );

        final EditText tmpStartTime = (EditText) v.findViewById(R.id.startTime);
        this.startTime = tmpStartTime;

        final EditText tmpEndTime = (EditText) v.findViewById(R.id.endTime);
        this.endTime = tmpEndTime;

        startTime.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {

                if(tmpStartTime.isFocused())
                    dialogStartTime.show();

            }
        });


        endTime.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {

                if(tmpEndTime.isFocused())
                    dialogEndTime.show();

            }
        });


        Spinner spinner = (Spinner) v.findViewById(R.id.spinner);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                    v.findViewById(R.id.textEventPriority).setVisibility(View.VISIBLE);
                    v.findViewById(R.id.seekBar).setVisibility(View.VISIBLE);
                    v.findViewById(R.id.textExamWeight).setVisibility(View.INVISIBLE);
                    v.findViewById(R.id.examWeight).setVisibility(View.INVISIBLE);

                if(i == 1)
                {
                    v.findViewById(R.id.textExamWeight).setVisibility(View.VISIBLE);
                    v.findViewById(R.id.examWeight).setVisibility(View.VISIBLE);
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        final EditText text = (EditText) v.findViewById(R.id.edit_name);


        text.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {


                if(!text.isFocused() && text.getText().length() != 0)
                    text.setBackgroundColor(Color.GREEN);
            }
        });

        final Button ok = (Button) v.findViewById(R.id.form_button);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });



        return  v;
    }









}
