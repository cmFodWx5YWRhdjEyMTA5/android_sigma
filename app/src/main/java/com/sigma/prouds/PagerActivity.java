package com.sigma.prouds;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.IBinder;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.sigma.prouds.base.BaseFragmentActivity;
import com.sigma.prouds.fragment.AssignmentFragment;
import com.sigma.prouds.fragment.HomeFragment;
import com.sigma.prouds.fragment.PerformanceFragment;
import com.sigma.prouds.fragment.TimesheetFragment;
import com.sigma.prouds.service.NotificationService;
import com.sigma.prouds.util.NotificationHelper;

public class PagerActivity extends BaseFragmentActivity {

    public static final String KEY_TO_DETAIL_PROJECT = "key_to_detail-project";
    public static final String KEY_TO_HOME_SEARCH = "key_to_home_search";
    public static final String KEY_PROJECT_ID = "key_project_id";
    public static final String KEY_PROJECT_NAME = "key_project_name";
    public static final String KEY_PROJECT_STATUS = "key_project_status";
    public static final String KEY_PROJECT_COMPLETED = "key_project_completed";
    public static final String KEY_SEARCH_LIST = "key_search_list";

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

        alarmSetUp();

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
                    fragment2 = AssignmentFragment.newInstance(PagerActivity.this);
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

    public void alarmSetUp()
    {
        //NotificationHelper.scheduleRepeatingRTCNotification(this, null, null);
        //NotificationHelper.enableBootReceiver(this);
        //startService(new Intent(this, NotificationService.class));
        //NotificationService notificationService = new NotificationService("service", this);
        //notificationService.startService(new Intent(this, notificationService.getClass()));
        Intent intent = new Intent(this, NotificationService.class);
        startService(intent);

        /*//startService(new Intent(this, notificationService));
        ServiceConnection serviceConnection = new ServiceConnection() {
            public void onServiceConnected(ComponentName className, IBinder service) {

            }

            public void onServiceDisconnected(ComponentName className) {

            }
        };
        bindService(new Intent(this, NotificationService.class), serviceConnection, BIND_AUTO_CREATE);*/
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
        if (bundle.containsKey(KEY_SEARCH_LIST)) {
            Intent intent = new Intent(this, HomeSearchActivity.class);
            intent.putExtra(KEY_TO_HOME_SEARCH, bundle);
            startActivity(intent);
        }
    }
}
