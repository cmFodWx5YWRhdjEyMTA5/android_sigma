package com.sigma.prouds;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.sigma.prouds.base.BaseFragmentActivity;
import com.sigma.prouds.fragment.AssignmentFragment;
import com.sigma.prouds.fragment.HomeFragment;
import com.sigma.prouds.fragment.PerformanceFragment;
import com.sigma.prouds.fragment.TimesheetFragment;

public class PagerActivity extends BaseFragmentActivity {

    private TabLayout tabLayout;
    private Toolbar tbHome, tbAssignment, tbTimesheet, tbPerformance;
    private Fragment fragment;
    private int[] tabIcons = {
            R.drawable.tab_home,
            R.drawable.tab_assignment,
            R.drawable.tab_timesheet,
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

        fragment = HomeFragment.newInstance(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
    }

    private void assignXML() {
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tbHome = (Toolbar) findViewById(R.id.toolbar_home);
        tbAssignment = (Toolbar) findViewById(R.id.toolbar_assignment);
        tbTimesheet = (Toolbar) findViewById(R.id.toolbar_timesheet);
        tbPerformance = (Toolbar) findViewById(R.id.toolbar_performance);
    }

    private void setTabLayout() {
        tabLayout.addTab(tabLayout.newTab().setIcon(tabIcons[0]));
        tabLayout.addTab(tabLayout.newTab().setIcon(tabIcons[1]));
        tabLayout.addTab(tabLayout.newTab().setIcon(tabIcons[2]));
        tabLayout.addTab(tabLayout.newTab().setIcon(tabIcons[3]));
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
                tbHome.setVisibility(View.VISIBLE);
                tbAssignment.setVisibility(View.INVISIBLE);
                tbTimesheet.setVisibility(View.INVISIBLE);
                tbPerformance.setVisibility(View.GONE);
                break;
            case 1:
                fragment = AssignmentFragment.newInstance(this);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
                tbHome.setVisibility(View.INVISIBLE);
                tbAssignment.setVisibility(View.VISIBLE);
                tbTimesheet.setVisibility(View.INVISIBLE);
                tbPerformance.setVisibility(View.INVISIBLE);
                break;
            case 2:
                fragment = TimesheetFragment.newInstance(this);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
                tbHome.setVisibility(View.INVISIBLE);
                tbAssignment.setVisibility(View.INVISIBLE);
                tbTimesheet.setVisibility(View.VISIBLE);
                tbPerformance.setVisibility(View.INVISIBLE);
                break;
            case 3:
                fragment = PerformanceFragment.newInstance(this);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
                tbHome.setVisibility(View.INVISIBLE);
                tbAssignment.setVisibility(View.INVISIBLE);
                tbTimesheet.setVisibility(View.INVISIBLE);
                tbPerformance.setVisibility(View.VISIBLE);
                break;
        }
    }

}
