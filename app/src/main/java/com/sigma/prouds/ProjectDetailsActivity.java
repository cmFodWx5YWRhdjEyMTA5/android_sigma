package com.sigma.prouds;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.sigma.prouds.adapter.ProjectDetailsAdapter;
import com.sigma.prouds.base.BaseActivity;
import com.sigma.prouds.fragment.ActivityFragment;
import com.sigma.prouds.fragment.OverviewFragment;
import com.sigma.prouds.fragment.WorkplanFragment;

public class ProjectDetailsActivity extends BaseActivity {

    private ViewPager vpProjectDetails;
    private TabLayout tlProjectDetails;

    @Override
    protected int getLayout() {
        return R.layout.activity_project_details;
    }

    @Override
    protected void workingSpace() {
        assignXML();
        setupViewPager(vpProjectDetails);
        tlProjectDetails.setupWithViewPager(vpProjectDetails);
    }

    public void assignXML() {
        vpProjectDetails = (ViewPager) findViewById(R.id.vp_projec_details);
        tlProjectDetails = (TabLayout) findViewById(R.id.tl_project_details);
    }

    public void setupViewPager(ViewPager viewPager) {
        ProjectDetailsAdapter adapter = new ProjectDetailsAdapter(getSupportFragmentManager());
        adapter.addFragment(new OverviewFragment(), "OVERVIEW");
        adapter.addFragment(new WorkplanFragment(), "WORKPLAN");
        adapter.addFragment(new ActivityFragment(), "ACTIVITY");
        viewPager.setAdapter(adapter);
    }
}
