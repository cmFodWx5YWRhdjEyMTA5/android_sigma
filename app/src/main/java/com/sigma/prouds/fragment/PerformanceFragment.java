package com.sigma.prouds.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
//import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sigma.prouds.ProfileSettingActivity;
import com.sigma.prouds.ProudsApplication;
import com.sigma.prouds.R;
import com.sigma.prouds.adapter.MyActivityAdapter;
import com.sigma.prouds.adapter.PerformanceAdapter;
import com.sigma.prouds.base.BaseFragment;
import com.sigma.prouds.adapter.PagerAdapter;

/**
 * Created by goy on 7/7/2017.
 */

public class PerformanceFragment extends BaseFragment
{
    static Context ctx;
    private ViewPager vpPerformance;
    private PerformanceAdapter adapter;
    private TextView tvUserName;
    private ProudsApplication app;
    private TabLayout tabLayout;
    private ImageView ivSetting;

    public static PerformanceFragment newInstance(Context context) {
        PerformanceFragment fragment = new PerformanceFragment();
        ctx = context;
        return fragment;
    }


    @Override
    protected int getLayout()
    {
        return R.layout.fragment_performance;
    }

    @Override
    protected void workingSpace(View view)
    {
        app = (ProudsApplication) ctx.getApplicationContext();
        vpPerformance = (ViewPager) view.findViewById(R.id.vp_performance);
        tvUserName = (TextView) view.findViewById(R.id.tv_username);
        tvUserName.setText(app.getSessionManager().getUserName());
        setViewPager(vpPerformance);

        tabLayout = (TabLayout) view.findViewById(R.id.tl_performance);
        tabLayout.setupWithViewPager(vpPerformance);

        ivSetting = (ImageView) view.findViewById(R.id.iv_setting);
        ivSetting.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), ProfileSettingActivity.class);
                intent.putExtra("user", tvUserName.getText().toString());
                startActivity(intent);
            }
        });

        query.id(R.id.tv_username).typeface(Typeface.createFromAsset(ctx.getAssets(), "lato_black.ttf"));
        query.id(R.id.tv_role).typeface(Typeface.createFromAsset(ctx.getAssets(), "lato_regular.ttf"));

    }

    private void setViewPager(ViewPager viewPager) {
        PagerAdapter adapter = new PagerAdapter(getChildFragmentManager(), ctx);
        adapter.addFragment(new ChartFragment().newInstance(ctx), "Performances");
        adapter.addFragment(new MyActivityFragment().newInstance(ctx), "My Activity");
        viewPager.setAdapter(adapter);
    }




}
