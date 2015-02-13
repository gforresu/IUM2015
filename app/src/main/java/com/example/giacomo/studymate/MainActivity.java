package com.example.giacomo.studymate;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.RectF;
import android.os.Bundle;
import android.provider.CalendarContract;
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


public class MainActivity extends FragmentActivity
{
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


        listaMenuLaterale = getResources().getStringArray(R.array.options_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        mTitle = listaMenuLaterale[0];

        mDrawerTitle = getTitle();

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
    private void selectItem(int position)
    {

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
        mTitle = listaMenuLaterale[position];
        mDrawerLayout.closeDrawer(mDrawerList);
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

            if (mDrawerToggle.onOptionsItemSelected(item))
            {
                return true;
            }

            //noinspection SimplifiableIfStatement
            if (id == R.id.action_settings)
            {
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








    }












