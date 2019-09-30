package com.example.vagiftdefrik.programforstudent2;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;

public class FragmentHome extends Fragment {

  ListView listNews;
  ProgressBar loader;

  ViewPager pager;
  TabLayout tabLayout;
  private HomeTabAdapter adapter;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View v = inflater.inflate(R.layout.fragment_home, container, false);

    tabLayout = (TabLayout) v.findViewById(R.id.tabs);
    pager = (ViewPager) v.findViewById(R.id.pager);

    adapter = new HomeTabAdapter(getChildFragmentManager());

    pager.setAdapter(adapter);
    pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

    tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
      @Override public void onTabSelected(TabLayout.Tab tab) {
        pager.setCurrentItem(tab.getPosition());
      }

      @Override public void onTabUnselected(TabLayout.Tab tab) {
      }

      @Override public void onTabReselected(TabLayout.Tab tab) {
      }
    });

    return v;
  }


}
