package com.example.vagiftdefrik.programforstudent2;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;


public class Menu extends AppCompatActivity {


    private ViewPager mViewPager;
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private DrawerLayout DL;
    private ActionBarDrawerToggle mToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_swipemenu);
        DL = (DrawerLayout) findViewById(R.id.swipe_menu);
        mToggle = new ActionBarDrawerToggle(this, DL, R.string.open, R.string.close);
        DL.addDrawerListener(mToggle);
        NavigationView nvDrawer = (NavigationView) findViewById(R.id.navigationview); ////////R.id.navigationview  <- eto aydi napisano v fayle activity_main_spepemenu
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setupDrawerContent(nvDrawer);
    }

    ///////////////////////////////////swipeMenu///////////////////////////////////
    // v etoy chasti koda mi budem opredilat najatie dlaya swipe menu dla perehoda vo vkladku

    public void selectItemDrawer(MenuItem menuItem) {
        Fragment myFragment = null;
        Class fragmentClass;
        switch (menuItem.getItemId()) {
            /*case R.id.home:
                fragmentClass =Home.class;
                break;*/
            case R.id.my_day:
                fragmentClass = MyDay.class;
                break;
            case R.id.dining:
                fragmentClass = Dining.class;
                break;
            case R.id.mobile_id:
                fragmentClass = MobileID.class;
                break;
            case R.id.bus:
                fragmentClass = ShuttleBus.class;
                break;
            case R.id.map:
                fragmentClass = Map.class;
                break;
            default:
                /*fragmentClass = Home.class;*/
                fragmentClass = MyDay.class;
        }
        try {
            myFragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flcontent, myFragment).commit(); /////R.id.flcontent eto activity_main_swipemenu
        menuItem.setChecked(true);
        setTitle(menuItem.getTitle());
        DL.closeDrawers();
    }
    private void setupDrawerContent(NavigationView navigationView){
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectItemDrawer(item);
                return true;
            }
        });
    }
////////////////////////////////////////////// swipeMenu/////////////////////////////////////////////////////////

   /* @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));



    }
*/

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        if (id == R.id.action_settings)
        ////////////////menu dla setting na glavnom ekrane
        {
            return true;
        }
        //////////////////////////////////////////////////////////////////
        if (mToggle.onOptionsItemSelected(item)){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //deleted PalceholderFragment class from her
    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
   /* public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    Tab1news tab1= new Tab1news();
                      *//* //Home_Page tab1 = new Home_Page();*//*
                    return tab1;
                case 1:
                    Library tab2 = new Library();
                    return tab2;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            // Show 2 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position){
            switch (position){
                case 0:
                    return "NEWS";
                case 1:
                    return "LIBRARY";
            }
            return null;
        }
    }*/

}