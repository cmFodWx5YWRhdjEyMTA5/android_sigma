package com.sigma.prouds;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

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
    private ProgressBar pbDetail;

    public static final String KEY_DRAWER_ITEM = "key_drawer_item";
    public static final String KEY_BACK_TO_OVERVIEW = "key_back_to_overview";

    private ImageView ivMenu, ivDrawerClose, ivBack, ivNotif;
    private TextView tvToolbarTitle, tvProject, tvPercen, tvStatus;
    private DrawerAdapter adapter;
    private DrawerLayout pdDrawer;
    private Fragment pdFragment = null;
    private FragmentManager pdFragmentManager = getSupportFragmentManager();
    private ListView pdListView;
    private Typeface latoBold, latoBlack, latoRegular;
    private int[] items = {R.string.drawer_overview,
        R.string.drawer_workplan,
        R.string.drawer_activity,
        R.string.drawer_issues,
        R.string.drawer_doc,
        R.string.drawer_team,
        R.string.drawer_setting};

    private String projectId;


    @Override
    protected int getLayout() {
        return R.layout.activity_project_details;
    }

    @Override
    protected void workingSpace() {
        assignXML();

        ivBack.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ProjectDetailsActivity.this.finish();
            }
        });

        ivNotif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toNotif();
            }
        });

        getDataFromHome();
        pdFragment = new OverviewFragment().newInstance(this, projectId);
        pdFragmentManager.beginTransaction().replace(R.id.pd_container, pdFragment).commit();

        drawerItems();
        query.id(R.id.iv_menu).clicked(this, "openDrawer");
        query.id(R.id.iv_drawerclose).clicked(this, "closeDrawer");

        latoBold = Typeface.createFromAsset(getAssets(), "lato_bold.ttf");
        latoBlack = Typeface.createFromAsset(getAssets(), "lato_black.ttf");
        latoRegular = Typeface.createFromAsset(getAssets(), "lato_regular.ttf");
        setTypeFace();
    }

    public void toNotif() {
        Intent intent = new Intent(this, NotifActivity.class);
        startActivity(intent);
    }

    public void assignXML() {
        ivMenu = (ImageView) findViewById(R.id.iv_menu);
        ivNotif = (ImageView) findViewById(R.id.iv_notif);
        ivDrawerClose = (ImageView) findViewById(R.id.iv_drawerclose);
        pdDrawer = (DrawerLayout) findViewById(R.id.pd_drawer);
        pdListView = (ListView) findViewById(R.id.lv_pd_drawer);
        pbDetail = (ProgressBar) findViewById(R.id.pb_project_details);
        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvToolbarTitle = (TextView) findViewById(R.id.tv_title_toolbar_assignment);
        tvProject = (TextView) findViewById(R.id.tv_pd_project);
        tvPercen = (TextView) findViewById(R.id.tv_pd_percen);
        tvStatus = (TextView) findViewById(R.id.tv_pd_status);
    }

    public void setTypeFace() {
        tvToolbarTitle.setTypeface(latoBlack);
        tvProject.setTypeface(latoBold);
        tvPercen.setTypeface(latoBlack);
        tvStatus.setTypeface(latoRegular);
    }

    public void getDataFromHome()
    {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras().getBundle(PagerActivity.KEY_TO_DETAIL_PROJECT);
        query.id(R.id.tv_pd_project).text(bundle.getString(PagerActivity.KEY_PROJECT_NAME));
        query.id(R.id.tv_pd_percen).text(bundle.getString(PagerActivity.KEY_PROJECT_COMPLETED)+"%");
        query.id(R.id.tv_pd_status).text(bundle.getString(PagerActivity.KEY_PROJECT_STATUS));

        float progress = Float.parseFloat(bundle.getString(PagerActivity.KEY_PROJECT_COMPLETED));
        int finalProgress = (int) progress;
        pbDetail.setProgress(finalProgress);

        projectId = bundle.getString(PagerActivity.KEY_PROJECT_ID);


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
                    pdFragment = new OverviewFragment().newInstance(this, projectId);
                    tvToolbarTitle.setText("PROJECT OVERVIEW");
                    break;
                case R.string.drawer_workplan:
                    pdFragment = new WorkplanFragment().newInstance(this, projectId);
                    tvToolbarTitle.setText("WORKPLAN");
                    break;
                case R.string.drawer_activity:
                    pdFragment = new ActivityFragment().newInstance(this, projectId);
                    tvToolbarTitle.setText("ACTIVITY");
                    break;
                case R.string.drawer_issues:
                    pdFragment = new IssuesFragment().newInstance(this, projectId);
                    tvToolbarTitle.setText("ISSUES");
                    break;
                case R.string.drawer_doc:
                    pdFragment = new DocFileFragment().newInstance(this, projectId);
                    tvToolbarTitle.setText("DOCS & FILES");
                    break;
                case R.string.drawer_team:
                    pdFragment = new TeamMemberFragment().newInstance(this, projectId);
                    tvToolbarTitle.setText("TEAM MEMBER");
                    break;
                case R.string.drawer_setting:
                    pdFragment = new ProjectSettingFragment().newInstance(this, projectId);
                    tvToolbarTitle.setText("PROJECT SETTING");
                    break;
            }

            pdFragmentManager.beginTransaction().replace(R.id.pd_container, pdFragment).commit();
            closeDrawer();
            //pdDrawer.closeDrawer(pdListView);
        }

        else if (bundle.containsKey(KEY_BACK_TO_OVERVIEW))
        {
            pdFragment = new OverviewFragment().newInstance(this, projectId);
            tvToolbarTitle.setText("PROJECT OVERVIEW");
            pdFragmentManager.beginTransaction().replace(R.id.pd_container, pdFragment).commit();
        }
    }

}
