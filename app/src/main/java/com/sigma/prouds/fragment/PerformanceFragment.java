package com.sigma.prouds.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
//import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
    
    private TabLayout tabLayout;


    public PerformanceFragment() {
        // Required empty public constructor
    }

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
        vpPerformance = (ViewPager) view.findViewById(R.id.vp_performance);
        setViewPager(vpPerformance);

        tabLayout = (TabLayout) view.findViewById(R.id.tl_performance);
        tabLayout.setupWithViewPager(vpPerformance);

    }

    private void setViewPager(ViewPager viewPager) {
        PagerAdapter adapter = new PagerAdapter(getActivity().getSupportFragmentManager(), ctx);
        adapter.addFragment(new MyActivityFragment().newInstance(ctx), "My Activity");
        adapter.addFragment(new ChartFragment().newInstance(ctx), "Performances");
        viewPager.setAdapter(adapter);
    }

}
