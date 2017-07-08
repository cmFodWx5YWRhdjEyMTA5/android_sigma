package com.sigma.prouds;

import android.support.design.widget.TabLayout;
import android.support.v4.view.*;

import com.sigma.prouds.base.BaseActivity;
import com.sigma.prouds.fragment.AssignmentFragment;
import com.sigma.prouds.fragment.HomeFragment;
import com.sigma.prouds.fragment.NotifFragment;
import com.sigma.prouds.fragment.PerformanceFragment;
import com.sigma.prouds.fragment.TimesheetFragment;

public class PagerActivity extends BaseActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
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
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        setTabIcons();
    }

    private void assignXML() {
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
    }

    private void setupViewPager(ViewPager viewPager) {
        com.sigma.prouds.adapter.PagerAdapter adapter = new com.sigma.prouds.adapter.PagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new HomeFragment());
        adapter.addFragment(new AssignmentFragment());
        adapter.addFragment(new TimesheetFragment());
        adapter.addFragment(new NotifFragment());
        adapter.addFragment(new PerformanceFragment());
        viewPager.setAdapter(adapter);
    }

    private void setTabIcons(){
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
        tabLayout.getTabAt(3).setIcon(tabIcons[3]);
        tabLayout.getTabAt(4).setIcon(tabIcons[4]);
    }
}
