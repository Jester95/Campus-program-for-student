package com.example.vagiftdefrik.programforstudent2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;




public class Home_Page extends AppCompatActivity {


  NavigationView nav_view;
  DrawerLayout drawer_layout;
  Toolbar toolbar;


  Fragment currentFrag = new FragmentHome();
  //MyDay myday =  new MyDay();\\    \
  String UserID;
  String Password;
  String Name;


  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.home_page);
    nav_view = (NavigationView) findViewById(R.id.nav_view);
    toolbar = (Toolbar) findViewById(R.id.toolbar);
    drawer_layout = (DrawerLayout) findViewById(R.id.drawer_layout);


    setSupportActionBar(toolbar);
    ActionBar actionbar = getSupportActionBar();
    actionbar.setDisplayHomeAsUpEnabled(true);
    actionbar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);

    Intent intent = getIntent();
    UserID = intent.getStringExtra("login");
    Password = intent.getStringExtra("password");
    Name = intent.getStringExtra("name");

    nav_view
        .setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
          @Override public boolean onNavigationItemSelected(MenuItem menuItem) {
            // set item as selected to persist highlight
            for (int i = 0; i < nav_view.getMenu().size(); i++) {
              nav_view.getMenu().getItem(i).setChecked(false);
            }

            menuItem.setChecked(true);
            // close drawer when item is tapped

            switch (menuItem.getItemId()) {

              case R.id.home:
                currentFrag = new FragmentHome();

                break;
              case R.id.login:
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                break;
              case R.id.map:
                currentFrag = new Map();

                break;
              case R.id.bus:
                currentFrag = new ShuttleBus();

                break;
              case R.id.mobile_id:
                Intent start_mobile_id = new Intent(Home_Page.this, MobileID.class);
                start_mobile_id.putExtra("login", UserID);
                start_mobile_id.putExtra("name", Name);
                startActivity(start_mobile_id);

                break;
              case R.id.dining:
                currentFrag = new Dining();

                break;
              case R.id.my_day:
                Intent start_myday = new Intent(Home_Page.this, MyDay.class);
                start_myday.putExtra("login", UserID);
                start_myday.putExtra("password", Password);
                startActivity(start_myday);


                break;
                default:
                break;

            }
            selectFrag(currentFrag);
            setTitle(menuItem.getTitle());

            drawer_layout.closeDrawers();

            return true;
          }
        });
    selectFrag(currentFrag);


  }


  void selectFrag(Fragment fragment) {
    FragmentManager fragmentManager = getSupportFragmentManager();
    fragmentManager.beginTransaction().replace(R.id.fragments_frame, fragment).commit();
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {

    switch (item.getItemId()) {
      case android.R.id.home:
        drawer_layout.openDrawer(GravityCompat.START);
        return true;
    }
    return super.onOptionsItemSelected(item);
  }


}



