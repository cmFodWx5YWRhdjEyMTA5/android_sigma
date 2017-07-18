package com.sigma.prouds;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.ListView;

import com.sigma.prouds.adapter.DrawerAdapter;
import com.sigma.prouds.base.BaseActivity;
import com.sigma.prouds.fragment.ActivityFragment;
import com.sigma.prouds.fragment.DocFileFragment;
import com.sigma.prouds.fragment.IssuesFragment;
import com.sigma.prouds.fragment.OverviewFragment;
import com.sigma.prouds.fragment.ProjectSettingFragment;
import com.sigma.prouds.fragment.TeamMemberFragment;
import com.sigma.prouds.fragment.WorkplanFragment;

import static android.R.attr.fragment;

public class ProjectDetailsActivity extends BaseActivity {

    private ViewPager vpProjectDetails;
    private TabLayout tlProjectDetails;

    public static final String KEY_DRAWER_ITEM = "key_drawer_item";
    private ImageView ivMenu, ivDrawerClose;
    private DrawerAdapter adapter;
    private DrawerLayout pdDrawer;
    private Fragment pdFragment = null;
    private FragmentManager pdFragmentManager = getSupportFragmentManager();
    private ListView pdListView;
    private int[] items = {R.string.drawer_overview,
        R.string.drawer_workplan,
        R.string.drawer_activity,
        R.string.drawer_issues,
        R.string.drawer_doc,
        R.string.drawer_team,
        R.string.drawer_setting};


    @Override
    protected int getLayout() {
        return R.layout.activity_project_details;
    }

    @Override
    protected void workingSpace() {
        assignXML();
        pdFragment = new OverviewFragment();
        pdFragmentManager.beginTransaction().replace(R.id.pd_container, pdFragment).commit();

        drawerItems();
        query.id(R.id.iv_menu).clicked(this, "openDrawer");
        query.id(R.id.iv_drawerclose).clicked(this, "closeDrawer");
    }

    public void assignXML() {
        ivMenu = (ImageView) findViewById(R.id.iv_menu);
        ivDrawerClose = (ImageView) findViewById(R.id.iv_drawerclose);
        pdDrawer = (DrawerLayout) findViewById(R.id.pd_drawer);
        pdListView = (ListView) findViewById(R.id.lv_pd_drawer);
    }

    public void drawerItems(){
        adapter = new DrawerAdapter(ProjectDetailsActivity.this, items);
        pdListView.setAdapter(adapter);
    }

    public void openDrawer() {
        pdDrawer.openDrawer(Gravity.END);
    }

    public void closeDrawer() {
        pdDrawer.closeDrawer(Gravity.END);
    }

    public void onEvent(Bundle bundle){
        if(bundle.containsKey(KEY_DRAWER_ITEM)){
            switch(bundle.getInt(KEY_DRAWER_ITEM)){
                case R.string.drawer_overview:
                    pdFragment = new OverviewFragment();
                    break;
                case R.string.drawer_workplan:
                    pdFragment = new WorkplanFragment();
                    break;
                case R.string.drawer_activity:
                    pdFragment = new ActivityFragment();
                    break;
                case R.string.drawer_issues:
                    pdFragment = new IssuesFragment();
                    break;
                case R.string.drawer_doc:
                    pdFragment = new DocFileFragment();
                    break;
                case R.string.drawer_team:
                    pdFragment = new TeamMemberFragment();
                    break;
                case R.string.drawer_setting:
                    pdFragment = new ProjectSettingFragment();
                    break;
            }

            pdFragmentManager.beginTransaction().replace(R.id.pd_container, pdFragment).commit();
            pdDrawer.closeDrawer(pdListView);
        }
    }

}
