package com.sigma.prouds;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.*;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toolbar;

import com.sigma.prouds.base.BaseActivity;
import com.sigma.prouds.base.BaseFragmentActivity;
import com.sigma.prouds.fragment.AssignmentFragment;
import com.sigma.prouds.fragment.HomeFragment;
import com.sigma.prouds.fragment.NotifFragment;
import com.sigma.prouds.fragment.PerformanceFragment;
import com.sigma.prouds.fragment.TimesheetFragment;

public class PagerActivity extends BaseFragmentActivity {

    private TabLayout tabLayout;
    private android.support.v7.widget.Toolbar toolbar;
    private RelativeLayout homeToolbar;
    private Fragment fragment;
    private int[] tabIcons = {
            R.drawable.tab_home,
            R.drawable.tab_assignment,
            R.drawable.timesheet,
            R.drawable.tab_notif,
            R.drawable.tab_performance
    };

    @Override
    protected int getLayout() {
        return R.layout.activity_pager;
    }

    @Override
    protected void workingSpace() {
        assignXML();
        setTabLayout();
        tabEvent();
    }

    private void assignXML() {
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        homeToolbar = (RelativeLayout) toolbar.findViewById(R.id.tb_home);
    }

    private void setTabLayout() {
        tabLayout.addTab(tabLayout.newTab().setIcon(tabIcons[0]));
        tabLayout.addTab(tabLayout.newTab().setIcon(tabIcons[1]));
        tabLayout.addTab(tabLayout.newTab().setIcon(tabIcons[2]));
        tabLayout.addTab(tabLayout.newTab().setIcon(tabIcons[3]));
        tabLayout.addTab(tabLayout.newTab().setIcon(tabIcons[4]));
    }

    public void tabEvent() {
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                setFragment(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    public void setFragment(int position) {
        switch (position) {
            case 0:
                fragment = HomeFragment.newInstance(this);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
                //TODO add view for toolbar
                break;
            case 1:
                fragment = AssignmentFragment.newInstance(this);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
                break;
            case 2:
                fragment = TimesheetFragment.newInstance(this);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
                break;
            case 3:
                fragment = NotifFragment.newInstance(this);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
                break;
            case 4:
                fragment = PerformanceFragment.newInstance(this);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
                break;
        }
    }

}
