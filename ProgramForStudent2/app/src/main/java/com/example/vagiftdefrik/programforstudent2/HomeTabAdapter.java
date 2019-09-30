package com.example.vagiftdefrik.programforstudent2;


import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class HomeTabAdapter extends FragmentStatePagerAdapter {

  int mNumOfTabs = 2;

  public HomeTabAdapter(FragmentManager fm) {
    super(fm);
  }

  @Override public Fragment getItem(int position) {
    switch (position) {
      case 0:
        return new FragmentTabHome();
      case 1:
        return new Library();
      default:
        return null;
    }
  }

  @Override public int getCount() {
    return mNumOfTabs;
  }




}