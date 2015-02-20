package com.example.giacomo.studymate;

import android.app.Activity;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.SimpleTimeZone;
import java.util.zip.Inflater;

/**
 * Crea la vista ed i listener per la creazione dell'evento
 */
public class CreateEventView
{
    public static final String EVENT_DATE = "event_date";
    private EditText eventName;
    private EditText endDate;
    private EditText startDate;
    private EditText startTime;
    private EditText endTime;
    private EditText cfu;
    private DatePickerDialog tmpEndDateDialog;
    private String minDate;
    private View view;
    private int counter =1;

    public CreateEventView()
    {

    }

    private void resetFields()
    {

        if(endTime != null)
            this.endTime.setBackgroundResource(R.drawable.ok_textfield_default_holo_light);

        if(startDate != null)
            this.startDate.setBackgroundResource(R.drawable.ok_textfield_default_holo_light);

        if(cfu != null)
            this.cfu.setBackgroundResource(R.drawable.ok_textfield_default_holo_light);

        if(endDate != null)
            this.endDate.setBackgroundResource(R.drawable.ok_textfield_default_holo_light);

        if(startTime != null)
            this.startTime.setBackgroundResource(R.drawable.ok_textfield_default_holo_light);


        if(eventName != null)
            this.eventName.setBackgroundResource(R.drawable.ok_textfield_default_holo_light);


    }


    public CreateEventView(final LayoutInflater inflater, final ViewGroup container,
                             Activity activity, final FragmentManager manager, String data)
    {



        //Cambio titolo all'actionbar
        activity.getActionBar().setTitle("Nuovo evento");

        //Imposto il listener dal datepicker
        DatePickerDialog.OnDateSetListener dateEndPickerListener = new DatePickerDialog.OnDateSetListener()
        {
            public void onDateSet(DatePicker view, int year,int month, int day)
            {



                GregorianCalendar d =new GregorianCalendar(year, month, day);

                SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

                String sel = format.format(d.getTime());

                endDate.setBackgroundResource(R.drawable.ok_textfield_activated_holo_light);

                addCounter();

                endDate.setText(sel);

                activateButton();
            }
    };
        DatePickerDialog.OnDateSetListener dateStartPickerListener = new DatePickerDialog.OnDateSetListener()
        {
            public void onDateSet(DatePicker view, int year,int month, int day)
            {



                GregorianCalendar d =new GregorianCalendar(year, month, day);

                SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

                String sel = format.format(d.getTime());

                startDate.setBackgroundResource(R.drawable.ok_textfield_activated_holo_light);

                addCounter();


       /*         Date tmpD= null;
                GregorianCalendar tmpCal = new GregorianCalendar();

                Date t = format.parse()*/

                if(tmpEndDateDialog != null)
                tmpEndDateDialog.getDatePicker().setMinDate(d.getTimeInMillis() + 1000);



                startDate.setText(sel);

                activateButton();
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

                startTime.setBackgroundResource(R.drawable.ok_textfield_activated_holo_light);

                addCounter();

                startTime.setText(ora);

                activateButton();

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


                endTime.setBackgroundResource(R.drawable.ok_textfield_activated_holo_light);

                addCounter();

                endTime.setText(ora);

                activateButton();

            }
        };
////////////////////////////////


        //Recupero la data
        //String data = activity.getString(EVENT_DATE);

        final View v = inflater.inflate(R.layout.new_event, container, false);

        //Imposto la data selezionata nel campo della data inizio dell'evento
        final EditText tmpStartDate = (EditText) v.findViewById(R.id.startDate);

        //EditText endDate = (EditText) v.findViewById(R.id.endDate);
        this.startDate = tmpStartDate;
        startDate.setBackgroundResource(R.drawable.ok_textfield_activated_holo_light);
        startDate.setText(data);


        //Picker fragment per la data fine
        final EditText tmpEndDate = (EditText) v.findViewById(R.id.endDate);
        this.endDate = tmpEndDate;


        Calendar cal = Calendar.getInstance();


        //Imposto limite al picker
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        Date tmpD= null;
        GregorianCalendar tmpCal = new GregorianCalendar();


        try
        {
           if(!data.isEmpty())
           {
               tmpD = format.parse(data);
               tmpCal.setTime(tmpD);
           }
        }catch(ParseException ex){System.out.print("Errore durante la conversione: "+ex.getMessage());}


        //Definizione dei picker della data
        final DatePickerDialog endDateDialog = new DatePickerDialog(activity, dateEndPickerListener ,
                tmpCal.get(Calendar.YEAR), tmpCal.get(Calendar.MONTH),tmpCal.get(Calendar.DAY_OF_MONTH));

        //Copio un riferimento
        this.tmpEndDateDialog = endDateDialog;

       if(tmpD != null)
        endDateDialog.getDatePicker().setMinDate(tmpCal.getTimeInMillis() + 1000);



        endDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {

                if (tmpEndDate.isFocused())
                    endDateDialog.show();

            }
        });


        final DatePickerDialog startDateDialog = new DatePickerDialog(activity, dateStartPickerListener ,
                cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH));


        startDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {

                if(tmpStartDate.isFocused())
                    startDateDialog.show();
            }
        });


        //Impostazione dei picker dell'ora

        final TimePickerDialog dialogStartTime = new TimePickerDialog(activity, timeStartPickerListener, 9, 0, true );
        final TimePickerDialog dialogEndTime = new TimePickerDialog(activity, timeEndPickerListener, 9, 0, true );

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


                activateButton();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        eventName = (EditText) v.findViewById(R.id.edit_name);


        eventName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {


                if(!b && eventName.getText().length() != 0)
                {
                    eventName.setBackgroundResource(R.drawable.ok_textfield_activated_holo_light);
                    addCounter();
                    activateButton();
                }


                else if(!b && eventName.getText().length() == 0)
                {
                    eventName.setBackgroundResource(R.drawable.err_textfield_activated_holo_light);
                    counter--;
                    eventName.setHint("Devi compilare questo campo");
                    eventName.setHintTextColor(Color.RED);
                    activateButton();
                }

            }
        });


        this.cfu = (EditText) v.findViewById(R.id.examWeight);

        cfu.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b)
            {

                if(!b && cfu.getText().length() != 0)
                {
                    cfu.setBackgroundResource(R.drawable.ok_textfield_activated_holo_light);
                    addCounter();
                    activateButton();

                }

                else if(!b && cfu.getText().length() == 0)
                {
                    cfu.setBackgroundResource(R.drawable.err_textfield_activated_holo_light);
                    counter--;

                    cfu.setHint("Compila");
                    cfu.setHintTextColor(Color.RED);
                    activateButton();

                }


            }
        });


        final Button ok = (Button) v.findViewById(R.id.form_button);

        final Button newEvent = (Button) v.findViewById(R.id.newEvent);



        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                ViewFragment k = new ViewFragment();
                Bundle args = new Bundle();
                args.putInt(ViewFragment.TYPE_CALENDAR_VIEW, 0);
                args.putBoolean(ViewFragment.AUTO_COMPLETE, true);

                k.setArguments(args);

                FragmentManager fragmentManager = manager;
                fragmentManager.beginTransaction()
                        .replace(R.id.content_frame, k)
                        .commit();


            }
        });

        newEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {


                ViewFragment k = new ViewFragment();
                Bundle args = new Bundle();
                args.putInt(ViewFragment.TYPE_CALENDAR_VIEW, 4);
                args.putString(ViewFragment.DATA, new String());

                k.setArguments(args);

                FragmentManager fragmentManager = manager;
                fragmentManager.beginTransaction()
                        .replace(R.id.content_frame, k)
                        .commit();

            }
        });




        this.view = v;



        if(data.length() == 0)
            resetFields();



    }



    public View getView(){return this.view;}


    private void activateButton()
    {

        Button fine = (Button) this.view.findViewById(R.id.form_button);
        Button newEvent = (Button) this.view.findViewById(R.id.newEvent);
        View exam = this.view.findViewById(R.id.examWeight);

        int limit = 5;

        if(exam.isShown())
            limit = 6;

        if(this.counter >= limit)
        {

            fine.setBackgroundColor(Color.parseColor("#39499c"));
            newEvent.setBackgroundColor(Color.parseColor("#39499c"));
        }


        else
        {
            fine.setBackgroundColor(Color.parseColor("#a7b0df"));
            newEvent.setBackgroundColor(Color.parseColor("#a7b0df"));
        }



    }

    private void addCounter()
    {
        if(counter <6)
            counter++;
    }






}
