package com.example.giacomo.studymate;

import android.app.Activity;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.res.Configuration;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.alamkanak.weekview.WeekView;
import com.alamkanak.weekview.WeekViewEvent;
import com.disegnator.robotocalendar.RobotoCalendarView;


public class MainActivity extends FragmentActivity {

    private DrawerLayout mDrawerLayout;
    private String[] listaMenuLaterale;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTitle = mDrawerTitle = getTitle();

        listaMenuLaterale = getResources().getStringArray(R.array.options_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);


        // Set the adapter for the list view
        mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_list_item, listaMenuLaterale));
        // Set the list's click listener
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                R.drawable.ic_drawer,  /* nav drawer image to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description for accessibility */
                R.string.drawer_close  /* "close drawer" description for accessibility */
        ) {
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

       // if (savedInstanceState == null) {
            selectItem(0);
        //}




    }


    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            selectItem(position);
        }
    }


    /**
     * Swaps fragments in the main content view
     */
    private void selectItem(int position) {
        // Create a new fragment and specify the planet to show based on position
        Fragment fragment = new ViewFragment();
        Bundle args = new Bundle();
        args.putInt(ViewFragment.TYPE_CALENDAR_VIEW, position);
        fragment.setArguments(args);

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content_frame, fragment)
                .commit();

        // Highlight the selected item, update the title, and close the drawer
        mDrawerList.setItemChecked(position, true);
        setTitle(listaMenuLaterale[position]);
        mDrawerLayout.closeDrawer(mDrawerList);
    }


    public class ViewFragment extends Fragment
    {
        public static final String TYPE_CALENDAR_VIEW = "type_view";
        Activity currentActivity = null;

        public ViewFragment()
        {

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState)
        {


            int i = getArguments().getInt(TYPE_CALENDAR_VIEW);

            View rootView = null;

            switch (i) {
                case 1: {

                    InWeekView week = new InWeekView(inflater, container);

                    rootView = week.getView();

                } break
                ;

                case 0:
                {
                    InMonthView month = new InMonthView(inflater, container);

                    rootView = month.getView();


                } break;


            }

            return rootView;
        }



    }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu_main, menu);
            return super.onCreateOptionsMenu(menu);
        }

       /* @Override
        public boolean onPrepareOptionsMenu(Menu menu) {
            // If the nav drawer is open, hide action items related to the content view
            // boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
            // menu.findItem(R.id.action_websearch).setVisible(!drawerOpen);
            return super.onPrepareOptionsMenu(menu);
        }
*/
        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();

            if (mDrawerToggle.onOptionsItemSelected(item)) {
                return true;
            }

            //noinspection SimplifiableIfStatement
            if (id == R.id.action_settings) {
                return true;
            }

            return super.onOptionsItemSelected(item);
        }



        @Override
        protected void onPostCreate(Bundle savedInstanceState) {
            super.onPostCreate(savedInstanceState);
            // Sync the toggle state after onRestoreInstanceState has occurred.
            mDrawerToggle.syncState();
        }



        @Override
        public void onConfigurationChanged(Configuration newConfig) {
            super.onConfigurationChanged(newConfig);
            // Pass any configuration change to the drawer toggls
            mDrawerToggle.onConfigurationChanged(newConfig);
        }



    /////////////////

    public class InWeekView
    {

        private WeekView mWeekView;
        private View rootView = null;

        public InWeekView(LayoutInflater inflater, ViewGroup container)
        {

            rootView = inflater.inflate(R.layout.week_layout, container, false);

            mWeekView = (WeekView) rootView.findViewById(R.id.weekView);


            mWeekView.setMonthChangeListener(new WeekView.MonthChangeListener() {
                @Override
                public List<WeekViewEvent> onMonthChange(int newYear, int newMonth) {
                    List<WeekViewEvent> events = new ArrayList<WeekViewEvent>();

                    /*Calendar startTime = Calendar.getInstance();
                    startTime.set(Calendar.HOUR_OF_DAY, 3);
                    startTime.set(Calendar.MINUTE, 0);
                    startTime.set(Calendar.MONTH, newMonth - 1);
                    startTime.set(Calendar.YEAR, newYear);
                    Calendar endTime = (Calendar) startTime.clone();
                    endTime.add(Calendar.HOUR, 1);
                    endTime.set(Calendar.MONTH, newMonth - 1);
                    WeekViewEvent event = new WeekViewEvent(1, getEventTitle(startTime), startTime, endTime);
                    //event.setColor(1);
                    events.add(event);*/


                    return events;
                }
            });

        }

            //setContentView(R.layout.week_layout);
            //rootView.findViewById(R.id.weekView);

        public View getView()
        {
            return this.rootView;
        }


        private String getEventTitle(Calendar time) {
            return String.format("Event of %02d:%02d %s/%d", time.get(Calendar.HOUR_OF_DAY), time.get(Calendar.MINUTE), time.get(Calendar.MONTH) + 1, time.get(Calendar.DAY_OF_MONTH));
        }


        public void onEventClick(WeekViewEvent event, RectF eventRect) {
            Toast.makeText(MainActivity.this, "Clicked " + event.getName(), Toast.LENGTH_SHORT).show();
        }


        public void onEventLongPress(WeekViewEvent event, RectF eventRect) {
            Toast.makeText(MainActivity.this, "Long pressed event: " + event.getName(), Toast.LENGTH_SHORT).show();
        }



    }



public class InMonthView implements RobotoCalendarView.RobotoCalendarListener
{
    private View rootView = null;
    private RobotoCalendarView robotoCalendarView;
    private Calendar currentCalendar;
    private int currentMonthIndex;

    public InMonthView(LayoutInflater inflater, ViewGroup container)
    {
        rootView = inflater.inflate(R.layout.month_layout, container, false);

        robotoCalendarView = (RobotoCalendarView) rootView.findViewById(R.id.robotoCalendarPicker);

        currentMonthIndex = 0;
        currentCalendar = Calendar.getInstance(Locale.getDefault());


        robotoCalendarView.setRobotoCalendarListener( this );
        robotoCalendarView.initializeCalendar(currentCalendar);
        robotoCalendarView.markDayAsCurrentDay(currentCalendar.getTime());


    }


    public View getView()
    {
        return this.rootView;
    }


    @Override
    public void onDateSelected(Date date)
    {

        this.robotoCalendarView.markDayAsSelectedDay(date);
    }

    @Override
    public void onRightButtonClick()
    {
        this.currentMonthIndex++;
        this.updateCalendar();

    }

    @Override
    public void onLeftButtonClick()
    {
        this.currentMonthIndex--;
        this.updateCalendar();
    }


    private void updateCalendar()
    {
        currentCalendar = Calendar.getInstance(Locale.getDefault());
        currentCalendar.add(Calendar.MONTH, currentMonthIndex);
        robotoCalendarView.initializeCalendar(currentCalendar);
    }





}











    }












