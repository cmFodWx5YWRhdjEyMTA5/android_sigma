package com.sigma.prouds.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sigma.prouds.R;
import com.sigma.prouds.adapter.MyActivityAdapter;
import com.sigma.prouds.adapter.PerformanceAdapter;
import com.sigma.prouds.base.BaseFragment;

/**
 * Created by goy on 7/7/2017.
 */

public class PerformanceFragment extends BaseFragment
{
    static Context ctx;
    private ViewPager vpPerformance;
    private PerformanceAdapter adapter;

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
        adapter = new PerformanceAdapter(getFragmentManager(), ctx);
        vpPerformance.setAdapter(adapter);
    }
}
