package com.sigma.prouds;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.sigma.prouds.base.BaseFragmentActivity;
import com.sigma.prouds.fragment.AssignmentFragment;
import com.sigma.prouds.fragment.HomeFragment;
import com.sigma.prouds.fragment.PerformanceFragment;
import com.sigma.prouds.fragment.TimesheetFragment;

public class PagerActivity extends BaseFragmentActivity {

    public static final String KEY_TO_DETAIL_PROJECT = "key_to_detail-project";
    public static final String KEY_PROJECT_ID = "key_project_id";
    public static final String KEY_PROJECT_NAME = "key_project_name";
    public static final String KEY_PROJECT_STATUS = "key_project_status";
    public static final String KEY_PROJECT_COMPLETED = "key_project_completed";

    private TabLayout tabLayout;
    private Toolbar tbHome, tbAssignment, tbTimesheet, tbPerformance;
    private Fragment fragment;

    private Fragment fragment1;
    private Fragment fragment2;
    private Fragment fragment3;
    private Fragment fragment4;

    private int[] tabIcons = {
            R.drawable.tab_home,
            R.drawable.tab_assignment,
            R.drawable.tab_timesheet,
            R.drawable.tab_performance
    };
    private ImageView ivHomeNotif, ivAssignmentNotif, ivPerformanceNotif, ivTimesheetNotif;

    @Override
    protected int getLayout() {
        return R.layout.activity_pager;
    }

    @Override
    protected void workingSpace() {
        assignXML();
        setTabLayout();
        tabEvent();
        setNotif();

        fragment1 = PerformanceFragment.newInstance(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment1).commit();
    }

    public void setNotif() {
        ivHomeNotif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toNotif();
            }
        });

        ivAssignmentNotif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toNotif();
            }
        });

        ivPerformanceNotif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toNotif();
            }
        });

        ivTimesheetNotif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toNotif();
            }
        });
    }

    private void assignXML() {
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tbHome = (Toolbar) findViewById(R.id.tb_home);
        tbAssignment = (Toolbar) findViewById(R.id.tb_assignment);
        tbTimesheet = (Toolbar) findViewById(R.id.tb_timesheet);
        tbPerformance = (Toolbar) findViewById(R.id.tb_performance);
        ivHomeNotif = (ImageView) findViewById(R.id.iv_home_notif);
        ivPerformanceNotif = (ImageView) findViewById(R.id.iv_performance_notif);
        ivAssignmentNotif = (ImageView) findViewById(R.id.iv_assignment_notif);
        ivTimesheetNotif = (ImageView) findViewById(R.id.iv_ts_notif);
        query.id(R.id.tv_title_toolbar_assignment).typeface(Typeface.createFromAsset(getAssets(), "lato_black.ttf"));
        query.id(R.id.tv_title_toolbar_timesheet).typeface(Typeface.createFromAsset(getAssets(), "lato_black.ttf"));
        query.id(R.id.tv_title_toolbar_myperformance).typeface(Typeface.createFromAsset(getAssets(), "lato_black.ttf"));
    }

    public void toNotif() {
        Intent intent = new Intent(this, NotifActivity.class);
        startActivity(intent);
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
                if (fragment1 == null)
                {
                    fragment1 = PerformanceFragment.newInstance(this);
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment1).commit();
                tbHome.setVisibility(View.INVISIBLE);
                tbAssignment.setVisibility(View.INVISIBLE);
                tbTimesheet.setVisibility(View.INVISIBLE);
                tbPerformance.setVisibility(View.VISIBLE);
                break;
            case 1:
                if (fragment2 == null)
                {
                    fragment2 = AssignmentFragment.newInstance(this);
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment2).commit();
                tbHome.setVisibility(View.INVISIBLE);
                tbAssignment.setVisibility(View.VISIBLE);
                tbTimesheet.setVisibility(View.INVISIBLE);
                tbPerformance.setVisibility(View.INVISIBLE);
                break;
            case 2:
                if (fragment3 == null)
                {
                    fragment3 = HomeFragment.newInstance(this);
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment3).commit();
                tbHome.setVisibility(View.VISIBLE);
                tbAssignment.setVisibility(View.INVISIBLE);
                tbTimesheet.setVisibility(View.INVISIBLE);
                tbPerformance.setVisibility(View.GONE);
                break;
            case 3:
                if (fragment4 == null)
                {
                    fragment4 = TimesheetFragment.newInstance(this);
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment4).commit();
                tbHome.setVisibility(View.INVISIBLE);
                tbAssignment.setVisibility(View.INVISIBLE);
                tbTimesheet.setVisibility(View.VISIBLE);
                tbPerformance.setVisibility(View.INVISIBLE);
                break;
        }
    }

    public void onEvent(Bundle bundle)
    {
        if (bundle.containsKey(KEY_PROJECT_ID))
        {
            //Log.i("project_id", bundle.getString(KEY_TO_DETAIL_PROJECT));
            Intent intent = new Intent(this, ProjectDetailsActivity.class);
            intent.putExtra(KEY_TO_DETAIL_PROJECT, bundle);
            startActivity(intent);
        }
    }
}
